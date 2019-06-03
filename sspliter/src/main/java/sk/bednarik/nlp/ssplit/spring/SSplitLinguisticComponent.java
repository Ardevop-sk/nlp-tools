package sk.bednarik.nlp.ssplit.spring;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

public class SSplitLinguisticComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new WordsToSentencesAnnotator();
  }
}
