package sk.bednarik.nlp.tagger.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import java.io.InputStream;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.tagger.OpenNLPPOSTaggerAnnotator;

public class POSTaggerComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try {
      InputStream inputStream = POSTaggerComponent.class.getClassLoader().getResourceAsStream(
          "sk.essentialdata.nlp/morphology/sk-pos-model.bin");
      return new OpenNLPPOSTaggerAnnotator(inputStream, false);
    } catch (IOException ex) {
      // TODO handle exception
      ex.printStackTrace();
    }
    return null;
  }
}
