package sk.bednarik.nlp.stemmer;

import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.utils.StemmerService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StemmerServiceConfig.class})
public class StemmerUtilsTest {

  @Autowired
  private StemmerService stemmerService;

  private String input = "Testovacia veta je smiešna.";

  @Test
  public void test2() {
    List<String> goldOutput = Lists.newArrayList("Testovak", "vet", "je", "smiešn", ".");
    List<CoreMap> labels = stemmerService.stem(input);
    List<String> output = labels
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(label -> label.get(CoreAnnotations.StemAnnotation.class))
        .collect(Collectors.toList());
    Assert.assertEquals(goldOutput, output);
  }

  @Test
  public void test3() {
    String input2 = "fazuľa\n" +
        "fazúľ\n";
    List<CoreMap> labels = stemmerService.stem(input2);
    List<String> output = labels
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(label -> label.get(CoreAnnotations.StemAnnotation.class))
        .collect(Collectors.toList());
    System.out.println(output);
  }
}
