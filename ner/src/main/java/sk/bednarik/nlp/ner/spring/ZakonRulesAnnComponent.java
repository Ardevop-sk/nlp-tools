package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.TokensRegexNERAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

public class ZakonRulesAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new TokensRegexNERAnnotator("rules/zakon.txt");
  }
}
