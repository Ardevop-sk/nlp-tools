package sk.bednarik.nlp.tagger;

import com.google.common.collect.Lists;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
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
import sk.bednarik.nlp.utils.TaggerService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaggerServiceConfig.class})
public class TaggerUtilsTest  {

  @Autowired
  private TaggerService taggerService;

  private String input = "Vláda Kanady a Európske spoločenstvo pre atómovú energiu.";
  private List<String> goldOutput = Lists
      .newArrayList("SSfs1", "SSfs2", "O", "AAns1x", "SSns1", "Eu4", "AAfs4x", "SSfs4", "Z");

  @Test//(enabled = false)
  public void test1() {
    List<CoreMap> labels = taggerService.tag(input);
    List<String> output = labels
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(CoreLabel::tag)
        .collect(Collectors.toList());
    Assert.assertEquals(goldOutput, output);
  }
}
