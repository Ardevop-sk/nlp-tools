package sk.bednarik.nlp.utils;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import java.util.List;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.sanitizer.spring.OrdinalSlashToBracketSanitizerComponent;
import sk.bednarik.nlp.sanitizer.spring.OriginalOffsetsComponent;
import sk.bednarik.nlp.sanitizer.spring.SpaceHighlightingSanitizerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, OriginalOffsetsComponent.class, SpaceHighlightingSanitizerComponent.class,
    OrdinalSlashToBracketSanitizerComponent.class})
public class SanitizerService {

  private final TokenizerComponent tokenizerComponent;
  private final SpaceHighlightingSanitizerComponent spaceHighlightingSanitizerComponent;
  private final OriginalOffsetsComponent originalOffsetsComponent;
  private final OrdinalSlashToBracketSanitizerComponent ordinalSlashToBracketSanitizerComponent;

  public SanitizerService(TokenizerComponent tokenizerComponent,
      SpaceHighlightingSanitizerComponent spaceHighlightingSanitizerComponent,
      OriginalOffsetsComponent originalOffsetsComponent,
      OrdinalSlashToBracketSanitizerComponent ordinalSlashToBracketSanitizerComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.spaceHighlightingSanitizerComponent = spaceHighlightingSanitizerComponent;
    this.originalOffsetsComponent = originalOffsetsComponent;
    this.ordinalSlashToBracketSanitizerComponent = ordinalSlashToBracketSanitizerComponent;
  }

  public List<CoreLabel> sanitize(String input) {
    return AnnotatorsUtils
        .annotateToTokens(input, spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent, tokenizerComponent, originalOffsetsComponent);
  }

  public AnnotationPipeline buildSanitizePipelien() {
    return AnnUtils.buildPipeline(spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent, tokenizerComponent, originalOffsetsComponent);
  }
}
