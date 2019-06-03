package sk.bednarik.nlp.sanitizer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.sanitizer.OrdinalSlashToBracketSanitizerAnnotator;

public class OrdinalSlashToBracketSanitizerComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new OrdinalSlashToBracketSanitizerAnnotator();
  }

}
