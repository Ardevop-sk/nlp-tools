package sk.bednarik.nlp.utils;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.anonymizer.spring.AnonymizerPipeline;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.commons.AsurAnnotations;
import sk.bednarik.nlp.ner.spring.NERHybridPipeline;
import sk.bednarik.nlp.spring.FSTLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.times.spring.SVKNumberComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, FSTLemmaComponent.class, POSTaggerComponent.class,
    SVKNumberComponent.class, NERHybridPipeline.class, AnonymizerPipeline.class})
public class AnonymizerService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final FSTLemmaComponent fstLemmaComponent;
  private final POSTaggerComponent posTaggerComponent;
  private final SVKNumberComponent svkNumberComponent;
  private final NERHybridPipeline nerHybridPipeline;
  private final AnonymizerPipeline anonymizerPipeline;

  public AnonymizerService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent, FSTLemmaComponent fstLemmaComponent,
      POSTaggerComponent posTaggerComponent, SVKNumberComponent svkNumberComponent,
      NERHybridPipeline nerHybridPipeline, AnonymizerPipeline anonymizerPipeline) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.fstLemmaComponent = fstLemmaComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.svkNumberComponent = svkNumberComponent;
    this.nerHybridPipeline = nerHybridPipeline;
    this.anonymizerPipeline = anonymizerPipeline;
  }

  public String anonymize(String input) {
    Annotation annotation = new Annotation(input);
    AnnotationPipeline pipeline = AnnUtils
        .buildPipeline(tokenizerComponent, splitLinguisticComponent, fstLemmaComponent, posTaggerComponent,
            svkNumberComponent, nerHybridPipeline, anonymizerPipeline);
    pipeline.annotate(annotation);
    return annotation.get(AsurAnnotations.ScrubbedTextAnnotation.class);
  }

}
