package sk.bednarik.nlp.anonymizer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import sk.bednarik.nlp.commons.AsurAnnotations;

/**
 * The type Report controller.
 *
 */
public class ScrubberAnnotatorTest {

  @Test
  public void annotateTest() {
    ScrubberAnnotator scrubberAnnotator = new ScrubberAnnotator();
    Annotation annotation = new Annotation("Ahoj som Filip Bednárik. Filip je cool.");
    String[][] tokens = new String[][]{
        {"Ahoj", "O"},
        {"som", "O"},
        {"Filip", "ANONYMOUS"},
        {"Bednárik", "ANONYMOUS"},
        {"Filip", "ANONYMOUS"},
        {"je", "O"},
        {"cool", "O"}};

    List<CoreLabel> sourceLabels = Stream.of(tokens)
        .map((token) -> {
          CoreLabel coreLabel = new CoreLabel();
          coreLabel.setOriginalText(token[0]);
          coreLabel.setNER(token[1]);
          return coreLabel;
        })
        .collect(Collectors.toList());

    annotation.set(CoreAnnotations.TokensAnnotation.class, sourceLabels);
    scrubberAnnotator.annotate(annotation);
    Assert.assertEquals("Ahoj som A. B. A. je cool ", annotation.get(AsurAnnotations.ScrubbedTextAnnotation.class));
  }
}
