package sk.bednarik.nlp.anonymizer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.anonymizer.ScrubberAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

public class ScrubberAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new ScrubberAnnotator();
  }
}
