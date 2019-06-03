package sk.bednarik.nlp.ner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.utils.NERService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {NERServiceConfig.class})
public class NerUtilsTest {

  @Autowired
  private NERService nerService;

  private static String text = "Špecializovaný trestný súd na verejnom zasadnutí konanom dňa 15. 01. 2019 v Pezinku 344/2009 Z. z. pred samosudcom Mgr. Ivanom Matelom, po schválení dohody o vine a treste podľa § 56 ods. 1, ods. 2 Trestného zákona. "
      + "Podľa § 497 Obchodného zákonníka, zmluvou o úvere sa zaväzuje veriteľ, že na požiadanie dlžníkaposkytne v jeho prospech peňažné prostriedky do určitej sumy, a dlžník sa zaväzuje poskytnuté peňažnéprostriedky vrátiť a zaplatiť úroky."
      + "Podľa § 52 ods. 1 a 2 prvá veta Občianskeho zákonníka, spotrebiteľskou zmluvou je každá zmluva bezohľadu na právnu formu, ktorú uzaviera dodávateľ so spotrebiteľom."
      + "Podľa ustanovenia § 262 ods. 1 a 2 CSP, o nároku na náhradu trov konania rozhodne aj bez návrhu súd v rozhodnutí, ktorým sa konanie končí.";
  //private static String text = "Od zajtra do pondelka. Dnes je 26.5.2016 a zajtra bude nedeľa. Čo s tým? Neviem. Včera som sa sprchoval a potom mi bolo zle. Pozajtra už nebude mlieko. V sobotu pôjdem na pivo z 5. storočia.";

  @Test
  public void testHybridNer() {
    List<String> output = nerService.hybridNER(text)
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(CoreLabel::ner)
        .collect(Collectors.toList());
    System.out.println(output);
  }

  @Test
  public void testLingNer() {
    List<CoreMap> output = nerService.lingNER(text);
    List<String> ners = output.stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(CoreLabel::ner)
        .collect(Collectors.toList());
    List<String> tokens = output.stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(CoreLabel::originalText).collect(Collectors.toList());
    List<String> lemmas = output.stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(CoreLabel::lemma)
        .collect(Collectors.toList());
    System.out.println(tokens);
    System.out.println(lemmas);
    System.out.println(ners);
  }
}
