package sk.bednarik.nlp.commons;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.Annotator;
import lombok.Getter;

/**
 * Extending this abstract class means, that the extended class is automatically singleton component
 */
@AnnComponentAnnotation
public abstract class AnnComponent {

  @Getter
  private final Annotator annotator;

  public AnnComponent() {
    this.annotator = prepareAnnotator();
  }

  public AnnComponent(AnnComponent...annComponents) {
    this.annotator = prepareAnnotator(annComponents);
  }

  private Annotator prepareAnnotator(AnnComponent...annComponents){
    return AnnUtils.buildPipeline(annComponents);
  }

  protected abstract Annotator prepareAnnotator();

  public void addAnnotatorToPipeline(AnnotationPipeline pipeline) {
    pipeline.addAnnotator(annotator);
  }

}
