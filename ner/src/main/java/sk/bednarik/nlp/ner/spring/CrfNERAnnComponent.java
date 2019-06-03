package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import java.io.IOException;
import sk.bednarik.nlp.commons.AnnComponent;

public class CrfNERAnnComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    try {
      CRFClassifier classifier = CRFClassifier.getClassifier("sk.essentialdata.nlp/ner/sk-ner-model-3_9_2.bin");
      NERClassifierCombiner nerClassifierCombiner = new NERClassifierCombiner(false, false, classifier);
      //TODO: EntityMentions, rules slovak ?
      NERCombinerAnnotator nerCombinerAnnotator = new NERCombinerAnnotator(nerClassifierCombiner, false, 1, 0,
          Integer.MAX_VALUE, false, true);
      return nerCombinerAnnotator;
    } catch (ClassNotFoundException | IOException e) {
      //TODO: Handle exception
      e.printStackTrace();
    }
    return null;
  }
}
