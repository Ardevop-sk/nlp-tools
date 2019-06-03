package sk.bednarik.nlp.ner.spring;

import edu.stanford.nlp.pipeline.Annotator;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.commons.AnnUtils;

@Import({TitulPredAnnComponent.class,
    TitulZaAnnComponent.class,
    MenoAnnComponent.class,
    PriezviskoAnnComponent.class,
    OsobaRulesAnnComponent.class,
    UlicaAnnComponent.class,
    AdresaRulesAnnComponent.class,
    MiscRulesAnnComponent.class,
    ZakonRulesAnnComponent.class,
    LegislativaRulesAnnComponent.class
})
public class NERLinguisticPipeline extends AnnComponent {

  public NERLinguisticPipeline(TitulPredAnnComponent titulPredAnnotatorComponent,
      TitulZaAnnComponent titulZaAnnotatorComponent,
      MenoAnnComponent menoAnnotatorComponent,
      PriezviskoAnnComponent priezviskoAnnotatorComponent,
      OsobaRulesAnnComponent osobaRulesAnnotatorComponent,
      UlicaAnnComponent ulicaAnnComponent,
      AdresaRulesAnnComponent adresaRulesAnnComponent,
      MiscRulesAnnComponent miscRulesAnnComponent,
      ZakonRulesAnnComponent zakonRulesAnnComponent,
      LegislativaRulesAnnComponent legislativaRulesAnnComponent) {
    super(
        titulPredAnnotatorComponent,
        titulZaAnnotatorComponent,
        menoAnnotatorComponent,
        priezviskoAnnotatorComponent,
        osobaRulesAnnotatorComponent,
        ulicaAnnComponent,
        adresaRulesAnnComponent,
        miscRulesAnnComponent,
        zakonRulesAnnComponent,
        legislativaRulesAnnComponent
    );
  }

  @Override
  protected Annotator prepareAnnotator() {
    throw new UnsupportedOperationException();
  }
}
