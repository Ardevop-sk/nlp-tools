package sk.bednarik.nlp.utils;

import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitStatisticComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, SSplitStatisticComponent.class})
public class SSplitService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final SSplitStatisticComponent splitStatisticComponent;

  public SSplitService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      SSplitStatisticComponent splitStatisticComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.splitStatisticComponent = splitStatisticComponent;
  }

  public List<CoreMap> ssplitStatistic(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitStatisticComponent);
  }

  public List<CoreMap> ssplitLinguistic(String input) {
    return AnnotatorsUtils.annotateToSentences(input, buildLinguisticPipeline());
  }

  public AnnotationPipeline buildLinguisticPipeline() {
    return AnnUtils.buildPipeline(tokenizerComponent, splitLinguisticComponent);
  }

}
