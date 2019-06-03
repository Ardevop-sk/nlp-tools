package sk.bednarik.nlp.sanitizer;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import sk.bednarik.nlp.commons.AsurAnnotations;

/**
 * Annotates offsets.
 *
 */
public class OriginalOffsetsAnnotator implements Annotator {

  @Override
  public void annotate(Annotation annotation) {
    String before = annotation.get(CoreAnnotations.OriginalTextAnnotation.class);
    int i = 0;
    List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);
    Iterator<CoreLabel> coreLabelIterator = tokens.iterator();
    CoreLabel currentToken = coreLabelIterator.next();
    int currentPositionInToken = 0;
    int start = 0;
    for (char oneChar : before.toCharArray()) {
      if (currentToken.originalText().toCharArray()[currentPositionInToken] == oneChar) {
        if (currentPositionInToken == 0) {
          start = i;
        }
        currentPositionInToken++;
      }

      if (currentToken.originalText().length() == currentPositionInToken) {
        currentToken.set(AsurAnnotations.OriginalCharacterOffsetBeginAnnotation.class, start);
        currentToken.set(AsurAnnotations.OriginalCharacterOffsetEndAnnotation.class, i + 1);
        currentPositionInToken = 0;
        if (coreLabelIterator.hasNext()) {
          currentToken = coreLabelIterator.next();
        } else {
          break;
        }
      }
      //ignore /r
      if (oneChar != 13) {
        i++;
      }
    }
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        AsurAnnotations.OriginalCharacterOffsetBeginAnnotation.class,
        AsurAnnotations.OriginalCharacterOffsetEndAnnotation.class
    )));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return new HashSet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.OriginalTextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class
    ));
  }
}
