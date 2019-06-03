package sk.bednarik.nlp.utils;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.parser.spring.ConlluComponent;
import sk.bednarik.nlp.parser.spring.DependencyParserComponent;
import sk.bednarik.nlp.spring.POSLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, POSTaggerComponent.class, POSLemmaComponent.class,
    DependencyParserComponent.class, ConlluComponent.class})
public class ParserService {

  private final TokenizerComponent tokenizerComponent;
  private final SSplitLinguisticComponent splitLinguisticComponent;
  private final POSTaggerComponent posTaggerComponent;
  private final POSLemmaComponent posLemmaComponent;
  private final DependencyParserComponent dependencyParserComponent;
  private final ConlluComponent conlluComponent;

  public ParserService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      POSTaggerComponent posTaggerComponent,
      POSLemmaComponent posLemmaComponent,
      DependencyParserComponent dependencyParserComponent,
      ConlluComponent conlluComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.posLemmaComponent = posLemmaComponent;
    this.dependencyParserComponent = dependencyParserComponent;
    this.conlluComponent = conlluComponent;
  }

  public List<CoreMap> parse(String input) {
    return AnnotatorsUtils
        .annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, posTaggerComponent, posLemmaComponent,
            dependencyParserComponent);
  }

  public List<CoreMap> parseWithConllu(String input) {
    return AnnotatorsUtils
        .annotateToSentences(input, tokenizerComponent, splitLinguisticComponent, posTaggerComponent, posLemmaComponent,
            dependencyParserComponent, conlluComponent);
  }

}
