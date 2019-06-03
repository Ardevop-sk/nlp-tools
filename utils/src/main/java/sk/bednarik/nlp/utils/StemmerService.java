package sk.bednarik.nlp.utils;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.stemmer.spring.StemmerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, StemmerComponent.class})
public class StemmerService {

  private final TokenizerComponent tokenizerComponent;

  private final SSplitLinguisticComponent splitLinguisticComponent;

  private final StemmerComponent stemmerComponent;

  public StemmerService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      StemmerComponent stemmerComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.stemmerComponent = stemmerComponent;
  }

  public List<CoreMap> stem(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, stemmerComponent);
  }
}
