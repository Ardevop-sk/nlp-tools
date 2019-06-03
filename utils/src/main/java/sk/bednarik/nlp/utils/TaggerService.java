package sk.bednarik.nlp.utils;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, POSTaggerComponent.class})
public class TaggerService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final POSTaggerComponent posTaggerComponent;

  public TaggerService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      POSTaggerComponent posTaggerComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.posTaggerComponent = posTaggerComponent;
  }

  public List<CoreMap> tag(String input) {
    return AnnotatorsUtils
        .annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, posTaggerComponent);
  }

  public AnnotationPipeline buildLinguisticPipeline() {
    return AnnUtils.buildPipeline(tokenizerComponent, splitLinguisticComponent, posTaggerComponent);
  }
}
