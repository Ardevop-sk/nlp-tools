package sk.bednarik.nlp.parser.spring;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.DependencyParseAnnotator;
import java.util.Properties;
import sk.bednarik.nlp.commons.AnnComponent;

public class DependencyParserComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    Properties properties = new Properties();
    properties.setProperty("model", "sk.essentialdata.nlp/parser/nndep.slovak.model.txt.gz");
    return new DependencyParseAnnotator(properties);
  }
}
