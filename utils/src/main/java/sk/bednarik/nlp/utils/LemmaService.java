package sk.bednarik.nlp.utils;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.spring.FSTLemmaComponent;
import sk.bednarik.nlp.spring.FSTLemmaKeepNotLemmatizedComponent;
import sk.bednarik.nlp.spring.POSLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, FSTLemmaComponent.class, FSTLemmaKeepNotLemmatizedComponent.class, POSLemmaComponent.class,
    POSTaggerComponent.class})
public class LemmaService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final FSTLemmaComponent fstLemmaComponent;
  private final FSTLemmaKeepNotLemmatizedComponent fstLemmaKeepNotLemmatizedComponent;
  private final POSLemmaComponent posLemmaComponent;
  private final POSTaggerComponent posTaggerComponent;

  public LemmaService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      @Qualifier("keepOriginal")
      FSTLemmaComponent fstLemmaComponent,
      @Qualifier("keepNotLemmatized")
      FSTLemmaKeepNotLemmatizedComponent fstLemmaKeepNotLemmatizedComponent,
      POSLemmaComponent posLemmaComponent,
      POSTaggerComponent posTaggerComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.fstLemmaComponent = fstLemmaComponent;
    this.posLemmaComponent = posLemmaComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.fstLemmaKeepNotLemmatizedComponent = fstLemmaKeepNotLemmatizedComponent;
  }

  public List<CoreMap> lemmatize(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, fstLemmaComponent);
  }

  public List<CoreMap> lemmatizeKeepNotLemmatized(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, fstLemmaKeepNotLemmatizedComponent);
  }

  public List<CoreMap> lemmatizeWithPOS(String input) {
    return AnnotatorsUtils.annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, posTaggerComponent,
        posLemmaComponent);
  }
}
