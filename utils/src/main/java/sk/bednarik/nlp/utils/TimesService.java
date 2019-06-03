package sk.bednarik.nlp.utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import sk.bednarik.nlp.commons.AnnUtils;
import sk.bednarik.nlp.spring.FSTLemmaComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tagger.spring.POSTaggerComponent;
import sk.bednarik.nlp.times.spring.SVKNumberComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;

@Service
@Import({TokenizerComponent.class, SSplitLinguisticComponent.class, FSTLemmaComponent.class, POSTaggerComponent.class,
    SVKNumberComponent.class})
public class TimesService {

  private TokenizerComponent tokenizerComponent;
  private SSplitLinguisticComponent splitLinguisticComponent;
  private FSTLemmaComponent fstLemmaComponent;
  private POSTaggerComponent posTaggerComponent;
  private SVKNumberComponent svkNumberComponent;

  public TimesService(TokenizerComponent tokenizerComponent,
      SSplitLinguisticComponent splitLinguisticComponent,
      FSTLemmaComponent fstLemmaComponent,
      POSTaggerComponent posTaggerComponent,
      SVKNumberComponent svkNumberComponent) {
    this.tokenizerComponent = tokenizerComponent;
    this.splitLinguisticComponent = splitLinguisticComponent;
    this.fstLemmaComponent = fstLemmaComponent;
    this.posTaggerComponent = posTaggerComponent;
    this.svkNumberComponent = svkNumberComponent;
  }

  public Annotation annotateTimes(String input, String docDate) {

    AnnotationPipeline pipeline = AnnUtils
        .buildPipeline(tokenizerComponent, splitLinguisticComponent, fstLemmaComponent, posTaggerComponent,
            svkNumberComponent);
    Annotation annotation = new Annotation(input);
    annotation.set(CoreAnnotations.DocDateAnnotation.class, docDate);
    pipeline.annotate(annotation);
    return annotation;
  }
  public Annotation annotateTimes(String input, LocalDate docDate) {
    return annotateTimes(input, docDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
  }
}
