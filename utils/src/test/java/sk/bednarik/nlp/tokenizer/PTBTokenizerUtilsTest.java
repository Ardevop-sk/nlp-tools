package sk.bednarik.nlp.tokenizer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.TestText;
import sk.bednarik.nlp.sanitizer.OrdinalSlashToBracketSanitizer;
import sk.bednarik.nlp.sanitizer.spring.OrdinalSlashToBracketSanitizerComponent;
import sk.bednarik.nlp.sanitizer.spring.OriginalOffsetsComponent;
import sk.bednarik.nlp.sanitizer.spring.SpaceHighlightingSanitizerComponent;
import sk.bednarik.nlp.tokenizer.spring.TokenizerComponent;
import sk.bednarik.nlp.utils.AnnotatorsUtils;
import sk.bednarik.nlp.utils.TokenizerService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TokenizerServiceConfig.class})
public class PTBTokenizerUtilsTest {

  @Autowired
  private TokenizerService tokenizerService;
  @Autowired
  private SpaceHighlightingSanitizerComponent spaceHighlightingSanitizerComponent;
  @Autowired
  private OriginalOffsetsComponent originalOffsetsComponent;
  @Autowired
  private TokenizerComponent tokenizerComponent;
  @Autowired
  private OrdinalSlashToBracketSanitizerComponent ordinalSlashToBracketSanitizerComponent;

  @Test
  @Ignore
  public void test1() {
    String input = "Testovacia veta obsahujúca s.r.o. so sídlom Námestie sv. Alžbety 5, Trenčín a dátum 21. 01. 2016. Nová veta IČO: 35 683 309. Dnes je 26. 5. 2016 a zajtra 27. marec 2016 bude nedeľa. V sobotu pôjdem na pivo z 5. storočia.";
    List<String> goldOutput = Lists
        .newArrayList("Testovacia", "veta", "obsahujúca", "s.r.o.", "so", "sídlom", "Námestie", "sv.", "Alžbety", "5",
            ",",
            "Trenčín", "a", "dátum", "21. 01. 2016", ".", "Nová", "veta", "IČO", ":", "35 683 309", ".", "Dnes", "je",
            "26. 5. 2016", "a", "zajtra", "27. marec 2016", "bude",
            "nedeľa", ".", "V", "sobotu", "pôjdem", "na", "pivo", "z", "5.", "storočia", ".");
    List<CoreLabel> labels = tokenizerService.tokenize(input);
    List<String> output = labels.stream().map(CoreLabel::word).collect(Collectors.toList());
    System.out.println(output);
    Assert.assertEquals(goldOutput, output);
  }

  @Test
  public void testText1Tokens() {
    List<CoreLabel> sentences = AnnotatorsUtils
        .annotateToTokens(TestText.text1.getInput(), spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent,
            tokenizerComponent, originalOffsetsComponent);
    List<String> output = sentences.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class))
        .collect(Collectors.toList());
    Assert.assertEquals(TestText.text1.getTokens(), output);
  }

  @Test
  public void testText2Tokens() {
    List<CoreLabel> sentences = AnnotatorsUtils
        .annotateToTokens(TestText.text2.getInput(), spaceHighlightingSanitizerComponent, ordinalSlashToBracketSanitizerComponent, tokenizerComponent, originalOffsetsComponent);
    List<String> output = sentences.stream().map(label -> label.get(CoreAnnotations.TextAnnotation.class))
        .collect(Collectors.toList());
//    output.stream().forEach(s -> { System.out.println(s); System.out.println("#"); });
    Assert.assertEquals(TestText.text2.getTokens(), output);
  }

  @Test
  public void test2() {
    String input = "Ahoj volám sa Filip";
    List<CoreLabel> labels = tokenizerService.tokenize(input);
    //TODO: Should match with SimpleNERAnnotatorTest
  }
  @Test
  public void test3() {
    String input = "(1) Trestnosť činu sa posudzuje podľa tohto zákona aj vtedy, ak to ustanovuje medzinárodná\n"
        + "zmluva, ktorá bola ratifikovaná a vyhlásená spôsobom ustanoveným zákonom, ktorou je\n"
        + "Slovenská republika viazaná.";
    List<CoreLabel> labels = tokenizerService.tokenize(input);
    List<String> output = labels.stream().map(CoreLabel::word).collect(Collectors.toList());
    System.out.println(output);
  }
}
