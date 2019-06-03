package sk.bednarik.nlp.spring;

import edu.stanford.nlp.pipeline.Annotator;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("keepOriginal")
public class FSTLemmaComponent extends AbstractFSTLemmaComponent {


  @Override
  protected Annotator prepareAnnotator() {
    return prepareAnnotator(true);
  }

}