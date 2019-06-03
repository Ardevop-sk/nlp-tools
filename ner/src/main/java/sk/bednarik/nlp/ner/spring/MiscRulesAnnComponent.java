package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.TokensRegexNERAnnotator;
import java.util.Properties;
import sk.bednarik.nlp.commons.AnnComponent;

public class MiscRulesAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    Properties miscProps = new Properties();
    miscProps.put("miscCrf.mapping.header", "true");
    miscProps.put("miscCrf.mapping","rules/misc.txt");
    return new TokensRegexNERAnnotator("miscCrf", miscProps);
  }
}
