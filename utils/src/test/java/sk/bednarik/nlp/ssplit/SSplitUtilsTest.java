package sk.bednarik.nlp.ssplit;

import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.TestText;
import sk.bednarik.nlp.sanitizer.spring.OrdinalSlashToBracketSanitizerComponent;
import sk.bednarik.nlp.sanitizer.spring.OriginalOffsetsComponent;
import sk.bednarik.nlp.sanitizer.spring.SpaceHighlightingSanitizerComponent;
import sk.bednarik.nlp.ssplit.spring.SSplitLinguisticComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;
import sk.bednarik.nlp.utils.AnnotatorsUtils;
import sk.bednarik.nlp.utils.SSplitService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SSplitServiceConfig.class})
public class SSplitUtilsTest {

  @Autowired
  private SSplitService splitService;

  @Autowired
  private SpaceHighlightingSanitizerComponent spaceHighlightingSanitizerComponent;
  @Autowired
  private OriginalOffsetsComponent originalOffsetsComponent;
  @Autowired
  private TokenizerComponent tokenizerComponent;
  @Autowired
  private SSplitLinguisticComponent splitLinguisticComponent;
  @Autowired
  private OrdinalSlashToBracketSanitizerComponent ordinalSlashToBracketSanitizerComponent;


  String input = "Testovacia veta obsahujúca s.r.o. so sídlom Námestie sv. Alžbety 5, Trenčín a dátum 21. 01. 2016. Nová veta IČO: 35 683 309. Dnes je 26. 5. 2016 a zajtra 27. marec 2016 bude nedeľa. V sobotu pôjdem na pivo z 5. storočia.";
  private List<String> goldOutputLin = Lists
      .newArrayList("Testovacia veta obsahujúca s.r.o. so sídlom Námestie sv. Alžbety 5, Trenčín a dátum 21. 01. 2016.",
          "Nová veta IČO: 35 683 309.", "Dnes je 26. 5. 2016 a zajtra 27. marec 2016 bude nedeľa.",
          "V sobotu pôjdem na pivo z 5. storočia.");
  private List<String> goldOutputStat = Lists
      .newArrayList("Testovacia veta obsahujúca s.r.o. so sídlom Námestie sv. Alžbety 5, Trenčín a dátum 21. 01. 2016.",
          "Nová veta IČO: 35 683 309.", "Dnes je 26. 5.", "2016 a zajtra 27. marec 2016 bude nedeľa.",
          "V sobotu pôjdem na pivo z 5. storočia.");

  @Test
  public void test1() {
    List<CoreMap> labels = splitService.ssplitLinguistic(input);
    List<String> output = labels.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class))
        .collect(Collectors.toList());
    output.stream().forEach(System.out::println);
    Assert.assertEquals(goldOutputLin, output);
  }

  @Ignore
  @Test
  public void test2() {
    List<CoreMap> labels = splitService.ssplitStatistic(input);
    List<String> output = labels.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class))
        .collect(Collectors.toList());
    Assert.assertEquals(goldOutputStat, output);
  }

  @Test
  public void test3() {
    List<CoreMap> labels = splitService.ssplitStatistic(
        "Okresný súd Bratislava I v právnej veci navrhovateľa - dlžníka: Peter Káplócky, s miestom podnikania Topoľčianska 3203/20, 851 01 Bratislava, IČO: 41 257 766 o návrhu na vyhlásenie konkurzu na majetok dlžníka: Peter Káplócky, s miestom podnikania Topoľčianska 3203/20, 851 01 Bratislava, IČO: 41 257 766\n"
            +
            "Rozhodnutie: Súd začína konkurzné konanie voči dlžníkovi: Peter Káplócky, s miestom podnikania Topoľčianska 3203/20, 851 01 Bratislava, IČO: 41 257 766.\n"
            +
            "Poučenie: Proti tomuto uzneseniu odvolanie nie je prípustné (§ 198 ods. 1 ZKR). Konkurzné konanie sa začína zverejnením tohto uznesenia v Obchodnom vestníku a má tieto účinky: a) dlžník je povinný obmedziť výkon činnosti len na bežné právne úkony; ak dlžník poruší túto povinnosť, platnosť právneho úkonu tým nie je dotknutá, právnemu úkonu však možno v konkurze odporovať, b) na majetok patriaci dlžníkovi nemožno začať konanie o výkon rozhodnutia alebo exekučné konanie; už začaté konania o výkon rozhodnutia alebo exekučné konania sa prerušujú, c) na majetok patriaci dlžníkovi nemožno pre záväzok dlžníka zabezpečený zabezpečovacím právom začať ani pokračovať vo výkone zabezpečovacieho práva; tento účinok sa nevzťahuje na výkon zabezpečovacieho práva vzťahujúceho sa na peňažné prostriedky, pohľadávky z účtu v banke alebo v pobočke zahraničnej banky, štátne dlhopisy, prevoditeľné cenné papiere alebo na pokračovanie vo výkone zabezpečovacieho práva dobrovoľnou dražbou podľa osobitného predpisu.");
    List<String> output = labels.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class))
        .collect(Collectors.toList());
    output.stream().forEach(System.out::println);
  }

  @Test
  public void testText1Sentences() {
    List<CoreMap> sentences = AnnotatorsUtils
        .annotateToSentences(TestText.text1.getInput(), spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent, tokenizerComponent,
            originalOffsetsComponent, splitLinguisticComponent);
    List<String> output = sentences.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class)).collect(Collectors.toList());
    Assert.assertEquals(TestText.text1.getSentences(), output);
  }

  @Test
  public void testText2Sentences() {
    List<CoreMap> sentences = AnnotatorsUtils
        .annotateToSentences(TestText.text2.getInput(), spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent, tokenizerComponent,
            originalOffsetsComponent, splitLinguisticComponent);
    List<String> output = sentences.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class)).collect(Collectors.toList());
    Assert.assertEquals(TestText.text2.getSentences(), output);
  }
}
