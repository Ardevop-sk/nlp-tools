package sk.bednarik.nlp.spring;

import edu.stanford.nlp.pipeline.Annotator;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Pavol Berta
 * @since 15. 3. 2019
 */
@Qualifier("keepNotLemmatized")
public class FSTLemmaKeepNotLemmatizedComponent extends AbstractFSTLemmaComponent {

  @Override
  protected Annotator prepareAnnotator() {
    return prepareAnnotator(false);
  }
}
