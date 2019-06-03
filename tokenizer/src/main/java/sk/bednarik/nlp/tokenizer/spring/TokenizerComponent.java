package sk.bednarik.nlp.tokenizer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.tokenizer.SVKTokenizerAnnotator;

public class TokenizerComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new SVKTokenizerAnnotator(false, "SLOVAK");
  }
}
