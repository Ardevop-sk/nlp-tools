package sk.bednarik.nlp.parser.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.parser.ConlluAnnotator;

public class ConlluComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new ConlluAnnotator();
  }
}
