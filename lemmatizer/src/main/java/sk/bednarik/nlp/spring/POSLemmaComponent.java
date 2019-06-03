package sk.bednarik.nlp.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import java.io.InputStream;
import sk.bednarik.nlp.POSLemmaAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

public class POSLemmaComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try (InputStream inputStream = POSLemmaComponent.class.getClassLoader().getResourceAsStream(
        "sk.essentialdata.nlp/morphology/slovnik.txt")) {
      return new POSLemmaAnnotator(inputStream);
    } catch (IOException e) {
      //TODO: Handle exception
      e.printStackTrace();
    }
    return null;
  }
}