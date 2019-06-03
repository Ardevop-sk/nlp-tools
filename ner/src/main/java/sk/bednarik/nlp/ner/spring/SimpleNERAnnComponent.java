package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.tuple.Pair;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.ner.SimpleNERAnnotator;

abstract class SimpleNERAnnComponent extends AnnComponent {

  protected abstract Pair<String,String[]> configure();

  @Override
  protected Annotator prepareAnnotator() {
    Pair<String,String[]> configuration = configure();
    try {
      InputStream inputStream = SimpleNERAnnComponent.class.getClassLoader().getResourceAsStream(configuration.getLeft());
      return new SimpleNERAnnotator(inputStream, configuration.getRight());
    } catch (IOException ex) {
      //TODO: Handle exception
      ex.printStackTrace();
    }
    return null;
  }
}
