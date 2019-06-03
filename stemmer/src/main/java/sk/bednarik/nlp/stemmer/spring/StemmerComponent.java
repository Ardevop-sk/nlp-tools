package sk.bednarik.nlp.stemmer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.stemmer.StemAnnotator;

public class StemmerComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try {
      return new StemAnnotator(false);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
