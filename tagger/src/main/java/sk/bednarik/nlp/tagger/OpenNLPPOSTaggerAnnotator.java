package sk.bednarik.nlp.tagger;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.Timing;
import edu.stanford.nlp.util.concurrent.MulticoreWrapper;
import edu.stanford.nlp.util.concurrent.ThreadsafeProcessor;
import edu.stanford.nlp.util.logging.Redwood;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class OpenNLPPOSTaggerAnnotator implements Annotator {

  private static Redwood.RedwoodChannels log = Redwood.channels(OpenNLPPOSTaggerAnnotator.class);

  private final POSTaggerME pos;

  private final int maxSentenceLength;

  private final int nThreads;

  public OpenNLPPOSTaggerAnnotator(InputStream inputStream, boolean verbose) throws IOException {
    this(loadModel(inputStream), Integer.MAX_VALUE, 1);
  }

  public OpenNLPPOSTaggerAnnotator(String posLoc, boolean verbose) throws IOException {
    this(posLoc, verbose, Integer.MAX_VALUE, 1);
  }

  public OpenNLPPOSTaggerAnnotator(String posLoc, boolean verbose, int maxSentenceLength, int numThreads)
      throws IOException {
    this(loadModel(posLoc, verbose), maxSentenceLength, numThreads);
  }

  public OpenNLPPOSTaggerAnnotator(POSTaggerME model) {
    this(model, Integer.MAX_VALUE, 1);
  }

  public OpenNLPPOSTaggerAnnotator(POSTaggerME model, int maxSentenceLength, int numThreads) {
    this.pos = model;
    this.maxSentenceLength = maxSentenceLength;
    this.nThreads = numThreads;
  }

  public OpenNLPPOSTaggerAnnotator(String annotatorName, Properties props) throws IOException {
    String posLoc = props.getProperty(annotatorName + ".model");
    assert posLoc != null;
    boolean verbose = PropertiesUtils.getBool(props, annotatorName + ".verbose", false);
    this.pos = loadModel(posLoc, verbose);
    this.maxSentenceLength = PropertiesUtils.getInt(props, annotatorName + ".maxlen", Integer.MAX_VALUE);
    this.nThreads = PropertiesUtils
        .getInt(props, annotatorName + ".nthreads", PropertiesUtils.getInt(props, "nthreads", 1));
  }

  public static String signature(Properties props) {
    return ("pos.maxlen:" + props.getProperty("pos.maxlen", "") +
        "pos.verbose:" + PropertiesUtils.getBool(props, "pos.verbose") +
        "pos.model:" + props.getProperty("pos.model", "null") +
        "pos.nthreads:" + props.getProperty("pos.nthreads", props.getProperty("nthreads", "")));
  }

  private static POSTaggerME loadModel(String loc, boolean verbose) throws IOException {
    Timing timer = null;
    if (verbose) {
      timer = new Timing();
      timer.doing("Loading POS Model [" + loc + ']');
    }
    POSModel model = new POSModel(new FileInputStream(loc));
    POSTaggerME tagger = new POSTaggerME(model);

    if (verbose) {
      timer.done();
    }
    return tagger;
  }

  private static POSTaggerME loadModel(InputStream inputStream) throws IOException {
    POSModel model = new POSModel(inputStream);
    return new POSTaggerME(model);
  }

  @Override
  public void annotate(Annotation annotation) {
    if (annotation.containsKey(CoreAnnotations.SentencesAnnotation.class)) {
      if (nThreads == 1) {
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
          doOneSentence(sentence);
        }
      } else {
        MulticoreWrapper<CoreMap, CoreMap> wrapper = new MulticoreWrapper<>(nThreads, new POSTaggerProcessor());
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
          wrapper.put(sentence);
          while (wrapper.peek()) {
            wrapper.poll();
          }
        }
        wrapper.join();
        while (wrapper.peek()) {
          wrapper.poll();
        }
      }
    } else {
      throw new RuntimeException("unable to find words/tokens in: " + annotation);
    }
  }

  private class POSTaggerProcessor implements ThreadsafeProcessor<CoreMap, CoreMap> {

    @Override
    public CoreMap process(CoreMap sentence) {
      return doOneSentence(sentence);
    }

    @Override
    public ThreadsafeProcessor<CoreMap, CoreMap> newInstance() {
      return this;
    }
  }

  private CoreMap doOneSentence(CoreMap sentence) {
    List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
    List<String> tokenStrings = tokens.stream().map(CoreLabel::originalText).collect(Collectors.toList());
    String[] tagged = null;
    if (tokens.size() <= maxSentenceLength) {
      try {
        tagged = pos.tag(tokenStrings.toArray(new String[tokenStrings.size()]));
      } catch (OutOfMemoryError e) {
        log.info("WARNING: Tagging of sentence ran out of memory. " +
            "Will ignore and continue: " +
            SentenceUtils.listToString(tokens));
      }
    }

    if (tagged != null) {
      for (int i = 0, sz = tokens.size(); i < sz; i++) {
        tokens.get(i).set(CoreAnnotations.PartOfSpeechAnnotation.class, tagged[i]);
      }
    } else {
      for (CoreLabel token : tokens) {
        token.set(CoreAnnotations.PartOfSpeechAnnotation.class, "X");
      }
    }
    return sentence;
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.CharacterOffsetBeginAnnotation.class,
        CoreAnnotations.CharacterOffsetEndAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class
    )));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.singleton(CoreAnnotations.PartOfSpeechAnnotation.class);
  }

}
