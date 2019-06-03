package sk.bednarik.nlp;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.io.RuntimeIOException;
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.logging.Redwood;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.lucene.store.InputStreamDataInput;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.fst.CharSequenceOutputs;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.Util;

import java.io.*;
import java.util.*;


public class LemmaAnnotator implements Annotator {

  private static Redwood.RedwoodChannels log = Redwood.channels(LemmaAnnotator.class);

  private final boolean keepOriginal;

  public LemmaAnnotator(String name, Properties properties) {
    String fstLoc = properties.getProperty(name + ".data");
    this.keepOriginal = Boolean.valueOf(properties.getProperty("keepOriginal", "true"));
    assert fstLoc != null;

    try (InputStream inputStream = new DataInputStream(IOUtils.getInputStreamFromURLOrClasspathOrFileSystem(fstLoc))) {
      fst = fstFromInputStream(inputStream);
    } catch (IOException e) {
      throw new RuntimeIOException("Error while loading a lemma data (probably missing data file)", e);
    }
  }

  public LemmaAnnotator(InputStream inputStream, Properties properties) throws IOException {
    fst = fstFromInputStream(inputStream);
    this.keepOriginal = Boolean.valueOf(properties.getProperty("keepOriginal", "true"));
  }

  private FST<CharsRef> fstFromInputStream(InputStream inputStream) throws IOException {
    return new FST<>(new InputStreamDataInput(new BufferedInputStream(inputStream)),
        CharSequenceOutputs.getSingleton());
  }

  public LemmaAnnotator(File file, Properties properties) throws IOException {
    fst = FST.read(file.toPath(), CharSequenceOutputs.getSingleton());
    this.keepOriginal = Boolean.valueOf(properties.getProperty("keepOriginal", "true"));
  }

  public void annotate(Annotation annotation) {
    if (annotation.containsKey(CoreAnnotations.SentencesAnnotation.class)) {
      annotation.get(CoreAnnotations.SentencesAnnotation.class)
          .forEach(sentence -> sentence.get(CoreAnnotations.TokensAnnotation.class)
              .forEach(token -> {
                String text = token.get(CoreAnnotations.TextAnnotation.class);
                token.set(CoreAnnotations.LemmaAnnotation.class,
                    lemmatize(text)
                        .orElse(lemmatizeLowerCase(text)
                            .orElse(lemmatizeProperCase(text)
                                .orElse(originalWord(text)
                                    .orElse(null)))));
              }));
    } else {
      throw new RuntimeException("Unable to find words/tokens in: " +
          annotation);
    }
  }

  private FST<CharsRef> fst;

  public Optional<String> getLemma(String text) {
    try {
      return Optional.of(Util.get(fst, new BytesRef(text)).toString().split("\\|")[0]);
    } catch (Exception exception) {
      return Optional.empty();
    }
  }

  private Optional<String> lemmatize(String text) {
    return getLemma(text);
  }

  private Optional<String> lemmatizeLowerCase(String text) {
    return getLemma(text.toLowerCase());
  }

  private Optional<String> lemmatizeProperCase(String text) {
    return getLemma(WordUtils.capitalizeFully(text));
  }

  private Optional<String> originalWord(String text) {
    return this.keepOriginal ? Optional.of(text) : Optional.empty();
  }


  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class
    )));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.singleton(CoreAnnotations.LemmaAnnotation.class);
  }
}



