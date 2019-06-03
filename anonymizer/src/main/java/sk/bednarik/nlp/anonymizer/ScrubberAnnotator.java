package sk.bednarik.nlp.anonymizer;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import sk.bednarik.nlp.commons.AsurAnnotations;

public class ScrubberAnnotator implements Annotator {

  public ScrubberAnnotator(String name, Properties properties) {
  }

  public ScrubberAnnotator() {
  }

  @Override
  public void annotate(Annotation annotation) {
    List<CoreLabel> sourceLabels = annotation.get(CoreAnnotations.TokensAnnotation.class);
    final AtomicInteger i = new AtomicInteger((int) 'A');
    HashMap<String, String> characterMap = new HashMap<>();

    List<CoreLabel> scrubbedTokens = sourceLabels.stream()
        .map(CoreLabel::new)
        .peek(scrubbedLabel -> {
          if (scrubbedLabel.ner().equals("ANONYMOUS")) {
            String originalText = scrubbedLabel.originalText();
            String firstLetter = scrubbedLabel.originalText().substring(0, 1).toLowerCase();
            if (firstLetter.matches("[\\p{L}]")) {
              String replacement = characterMap.computeIfAbsent(firstLetter, key ->
                  String.valueOf((char) i.getAndIncrement())
              );
              scrubbedLabel.setOriginalText(replacement + ".");
            } else {
              scrubbedLabel.setOriginalText(originalText.replaceAll("[0-9]", "X"));
            }
          }
        })
        .collect(Collectors.toList());

    String scrubbedText = SentenceUtils.listToOriginalTextString(scrubbedTokens);
    annotation.set(AsurAnnotations.ScrubbedTextAnnotation.class, scrubbedText);
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return null;
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class,
        CoreAnnotations.NamedEntityTagAnnotation.class
    )));
  }
}
