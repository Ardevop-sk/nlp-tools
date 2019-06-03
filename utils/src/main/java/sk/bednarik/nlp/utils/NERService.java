package sk.bednarik.nlp.utils;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.ner.spring.NERHybridPipeline;
import sk.bednarik.nlp.ner.spring.NERLinguisticPipeline;
import sk.bednarik.nlp.spring.FSTLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.stemmer.spring.StemmerComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.times.spring.SVKNumberComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, FSTLemmaComponent.class, POSTaggerComponent.class,
    SVKNumberComponent.class, NERLinguisticPipeline.class, StemmerComponent.class, NERHybridPipeline.class})
public class NERService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final FSTLemmaComponent fstLemmaComponent;
  private final StemmerComponent stemmerComponent;
  private final POSTaggerComponent posTaggerComponent;
  private final SVKNumberComponent svkNumberComponent;
  private final NERLinguisticPipeline nerLinguisticPipeline;
  private final NERHybridPipeline nerHybridPipeline;

  public NERService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      FSTLemmaComponent fstLemmaComponent,
      StemmerComponent stemmerComponent,
      POSTaggerComponent posTaggerComponent,
      SVKNumberComponent svkNumberComponent,
      NERLinguisticPipeline nerLinguisticPipeline,
      NERHybridPipeline nerHybridPipeline) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.fstLemmaComponent = fstLemmaComponent;
    this.stemmerComponent = stemmerComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.svkNumberComponent = svkNumberComponent;
    this.nerLinguisticPipeline = nerLinguisticPipeline;
    this.nerHybridPipeline = nerHybridPipeline;
  }

  public List<CoreMap> lingNER(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, fstLemmaComponent,
        stemmerComponent, posTaggerComponent, svkNumberComponent, nerLinguisticPipeline);
  }

  public List<CoreMap> hybridNER(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, fstLemmaComponent,
        posTaggerComponent, svkNumberComponent, nerHybridPipeline);
  }
}
