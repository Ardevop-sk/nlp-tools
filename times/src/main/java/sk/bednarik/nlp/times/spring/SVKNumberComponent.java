package sk.bednarik.nlp.times.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.util.Properties;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.times.SVKNumberAnnotator;

public class SVKNumberComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    Properties sutimeConfig = new Properties();
    sutimeConfig.put("sutime.rules",
        "edu/stanford/nlp/models/sutime/defs.sutime.txt,rules/slovak.sutime.txt,rules/slovak.holidays.sutime.txt");
    sutimeConfig.put("sutime.verbose", "false");

    return new SVKNumberAnnotator(false, true, sutimeConfig);
  }
}
