package sk.bednarik.nlp.synonyms;

import static org.assertj.core.api.Assertions.assertThat;

import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.commons.AsurAnnotations;
import sk.bednarik.nlp.utils.SynonymsService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SynonymsServiceConfig.class})
public class SynonymsUtilsTest {

  @Autowired
  private SynonymsService synonymsService;

  @Test
  @Ignore
  public void testSynonyms1() {
    List<CoreMap> sentencesMap = synonymsService.findSynonyms("Nie je potrebné hľadať dôvod.");
    assertThat(sentencesMap.get(0).get(AsurAnnotations.PhraseAnnotation.class))
        .flatExtracting(phrase -> phrase.get(AsurAnnotations.Synonyms.class))
        .contains("nepotrebný", "súrny", "príčina");
  }

  @Test
  @Ignore
  public void testSynonyms2() {
    List<CoreMap> sentencesMap = synonymsService
        .findSynonyms("Smrť nastala pred naším letopočtom.");
    assertThat(sentencesMap.get(0).get(AsurAnnotations.PhraseAnnotation.class))
        .flatExtracting(phrase -> phrase.get(AsurAnnotations.Synonyms.class))
        .contains("úmrtie", "pred Kristom");
  }

  @Test
  @Ignore
  public void testSynonyms3() {
    List<CoreMap> sentencesMap = synonymsService.findSynonyms("Pozriem si ešte jeden film.");
    assertThat(sentencesMap.get(0).get(AsurAnnotations.PhraseAnnotation.class))
        .flatExtracting(phrase -> phrase.get(AsurAnnotations.Synonyms.class))
        .contains("pohliadnuť", "ďalší");
  }
}
