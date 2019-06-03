package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.commons.AnnComponent;

@Import({NERLinguisticPipeline.class,
    StackedNERTagAnnComponent.class,
    CrfNERAnnComponent.class,
    MiscCrfRulesAnnComponent.class})
public class NERHybridPipeline extends AnnComponent {

  public NERHybridPipeline(NERLinguisticPipeline nerLinguisticPipeline,
      StackedNERTagAnnComponent stackedNERTagAnnComponent,
      CrfNERAnnComponent cRFNERAnnComponent,
      MiscCrfRulesAnnComponent miscCrfRulesAnnComponent) {
    super(nerLinguisticPipeline,
        stackedNERTagAnnComponent,
        cRFNERAnnComponent,
        miscCrfRulesAnnComponent);
  }

  @Override
  protected Annotator prepareAnnotator() {
    throw new UnsupportedOperationException();
  }
}
