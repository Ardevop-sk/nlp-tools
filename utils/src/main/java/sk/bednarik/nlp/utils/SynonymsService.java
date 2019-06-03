package sk.bednarik.nlp.utils;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.parser.spring.DependencyParserComponent;
import sk.bednarik.nlp.spring.POSLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.synonyms.spring.SynonymsComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, POSTaggerComponent.class, POSLemmaComponent.class,
    DependencyParserComponent.class, SynonymsComponent.class})
public class SynonymsService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final POSTaggerComponent posTaggerComponent;
  private final POSLemmaComponent posLemmaComponent;
  private final DependencyParserComponent dependencyParserComponent;
  private final SynonymsComponent synonymsComponent;

  public SynonymsService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      POSTaggerComponent posTaggerComponent,
      POSLemmaComponent posLemmaComponent,
      DependencyParserComponent dependencyParserComponent,
      SynonymsComponent synonymsComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.posLemmaComponent = posLemmaComponent;
    this.dependencyParserComponent = dependencyParserComponent;
    this.synonymsComponent = synonymsComponent;
  }

  public List<CoreMap> findSynonyms(String input) {
    return AnnotatorsUtils
        .annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, posTaggerComponent, posLemmaComponent,
            dependencyParserComponent, synonymsComponent);
  }
}
