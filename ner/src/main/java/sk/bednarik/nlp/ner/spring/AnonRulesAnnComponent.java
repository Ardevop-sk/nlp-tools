package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.TokensRegexNERAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

public class AnonRulesAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return new TokensRegexNERAnnotator("rules/anonymous.txt");
  }
}
