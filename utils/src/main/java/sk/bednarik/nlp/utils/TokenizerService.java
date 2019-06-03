package sk.bednarik.nlp.utils;

import edu.stanford.nlp.ling.CoreLabel;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class})
public class TokenizerService {

  private final TokenizerComponent tokenizerComponent;

  public TokenizerService(TokenizerComponent tokenizerComponent) {
    this.tokenizerComponent = tokenizerComponent;
  }

  public List<CoreLabel> tokenize(String input) {
    return AnnotatorsUtils.annotateToTokens(input, tokenizerComponent);
  }
}
