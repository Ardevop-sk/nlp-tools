package sk.bednarik.nlp.utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.commons.AnnUtils;

@Slf4j
public class AnnotatorsUtils {

  public static List<CoreMap> annotateToSentences(String input, AnnotationPipeline pipeline) {
    Annotation annotation = new Annotation(input);
    pipeline.annotate(annotation);
    return annotation.get(CoreAnnotations.SentencesAnnotation.class);
  }

  public static List<CoreMap> annotateToSentences(String input, AnnComponent... annComponents) {
    return annotateToSentences(input, AnnUtils.buildPipeline(annComponents));
  }

  private static List<CoreLabel> annotateToTokens(String input, AnnotationPipeline pipeline) {
    Annotation annotation = new Annotation(input);
    pipeline.annotate(annotation);
    return annotation.get(CoreAnnotations.TokensAnnotation.class);
  }

  public static List<CoreLabel> annotateToTokens(String input, AnnComponent... annComponents) {
    return annotateToTokens(input, AnnUtils.buildPipeline(annComponents));
  }

}