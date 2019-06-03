package sk.bednarik.nlp.anonymizer.spring;

import edu.stanford.nlp.pipeline.Annotator;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.ner.spring.AnonRulesAnnComponent;
import sk.bednarik.nlp.ner.spring.StackedNERTagAnnComponent;

@Import({NERAnonAnnComponent.class, AnonRulesAnnComponent.class, ScrubberAnnComponent.class})
public class AnonymizerPipeline extends AnnComponent {

  public AnonymizerPipeline(StackedNERTagAnnComponent stackedNERTagAnnComponent,
      NERAnonAnnComponent NERAnonAnnComponent,
      AnonRulesAnnComponent anonRulesAnnComponent,
      ScrubberAnnComponent scrubberAnnComponent) {
    super(
        stackedNERTagAnnComponent,
        NERAnonAnnComponent,
        anonRulesAnnComponent,
        scrubberAnnComponent
    );
  }

  @Override
  protected Annotator prepareAnnotator() {
    return null;
  }
}
