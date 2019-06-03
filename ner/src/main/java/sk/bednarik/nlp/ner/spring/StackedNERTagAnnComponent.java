package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.ner.StackedNamedEntityTagAnnotator;

public class StackedNERTagAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new StackedNamedEntityTagAnnotator();
  }
}
