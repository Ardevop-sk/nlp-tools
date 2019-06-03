package sk.bednarik.nlp;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.logging.Redwood;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

public class POSLemmaAnnotator implements Annotator {

  private HashMap<String, String> lemmaMap;
  private final boolean keepOriginal;

  private static Redwood.RedwoodChannels log = Redwood.channels(POSLemmaAnnotator.class);

  public POSLemmaAnnotator(InputStream file) {
    this(file, new Properties());
  }

  public POSLemmaAnnotator(InputStream file, Properties properties) {
    lemmaMap = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file, "UTF-8"))) {
      br.lines()
          .map(line -> line.split("\t"))
          .forEach(parts -> lemmaMap.computeIfAbsent((parts[1] + "_" + parts[2]).toLowerCase(), key -> parts[0]));
    } catch (IOException e) {
      log.error(e);
    }
    this.keepOriginal = Boolean.valueOf(properties.getProperty("keepOriginal", "true"));
  }

  public void annotate(Annotation annotation) {
    if (annotation.containsKey(CoreAnnotations.SentencesAnnotation.class)) {
      annotation.get(CoreAnnotations.SentencesAnnotation.class)
          .forEach(sentence -> sentence.get(CoreAnnotations.TokensAnnotation.class)
              .forEach(token -> token.set(CoreAnnotations.LemmaAnnotation.class,
                  getLemma(token.get(CoreAnnotations.TextAnnotation.class),
                      token.get(CoreAnnotations.PartOfSpeechAnnotation.class)))));
    } else {
      throw new RuntimeException("Unable to find words/tokens in: " +
          annotation);
    }
  }

  private String getLemma(String text, String posTag) {
    return Optional.ofNullable(lemmaMap.get((text + "_" + posTag).toLowerCase()))
        .orElse(originalWord(text).orElse(null));
  }

  private Optional<String> originalWord(String text) {
    return this.keepOriginal ? Optional.of(text) : Optional.empty();
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class,
        CoreAnnotations.PartOfSpeechAnnotation.class
    )));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.singleton(CoreAnnotations.LemmaAnnotation.class);
  }
}



