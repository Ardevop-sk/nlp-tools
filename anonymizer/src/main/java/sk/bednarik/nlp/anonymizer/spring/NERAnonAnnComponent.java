package sk.bednarik.nlp.anonymizer.spring;

import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import java.io.IOException;
import sk.bednarik.nlp.commons.AnnComponent;

public class NERAnonAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try {
      CRFClassifier anonCRFClassifier = CRFClassifier.getClassifier("sk.essentialdata.nlp/anonymizer/sk-anon-model.bin");
      NERClassifierCombiner anonCRFClassifierCombiner = new NERClassifierCombiner(false, false, anonCRFClassifier);
      //TODO: EntityMentions, rules slovak ?
      NERCombinerAnnotator anonCRFClassifierAnnotator = new NERCombinerAnnotator(anonCRFClassifierCombiner, false, 1, 0,
          Integer.MAX_VALUE, false, true);
      return anonCRFClassifierAnnotator;
    } catch (ClassNotFoundException | IOException e) {
      //TODO: Handle exception
      e.printStackTrace();
    }
    return null;
  }
}
