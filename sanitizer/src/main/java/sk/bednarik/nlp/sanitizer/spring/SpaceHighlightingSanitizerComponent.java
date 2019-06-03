package sk.bednarik.nlp.sanitizer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.sanitizer.SpaceHighlightingSanitizerAnnotator;

public class SpaceHighlightingSanitizerComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new SpaceHighlightingSanitizerAnnotator();
  }
}
