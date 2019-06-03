package sk.bednarik.nlp.ssplit.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import java.io.InputStream;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.ssplit.OpenNLPSentenceSplitterAnnotator;

public class SSplitStatisticComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try (InputStream inputStream = SSplitStatisticComponent.class.getClassLoader()
        .getResourceAsStream("sk.essentialdata.nlp/ssplit/sk-sentence-model.bin")) {
      return new OpenNLPSentenceSplitterAnnotator(false, false, inputStream);
    } catch (IOException e) {
      //TODO: Handle Exception
      e.printStackTrace();
    }
    return null;
  }
}
