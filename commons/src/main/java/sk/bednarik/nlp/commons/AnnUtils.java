package sk.bednarik.nlp.commons;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import java.util.Arrays;

public class AnnUtils {

  public static AnnotationPipeline buildPipeline(AnnComponent... annComponents) {
    AnnotationPipeline pipeline = new AnnotationPipeline();
    Arrays.asList(annComponents)
        .forEach(annotatorComponent -> annotatorComponent.addAnnotatorToPipeline(pipeline));
    return pipeline;
  }
}
