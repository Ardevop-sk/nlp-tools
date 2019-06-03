package sk.bednarik.nlp.parser;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.utils.ParserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ParserServiceConfig.class)
public class ParserUtilsTest {

  @Autowired
  private ParserService parserService;

  @Test
  public void testParser() {
    List<CoreMap> sentencesMap = parserService.parseWithConllu("Nie je potrebné hľadať dôvod.");
    List<String> output = sentencesMap
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(token -> token.get(CoreAnnotations.CoNLLDepTypeAnnotation.class))
        .collect(Collectors.toList());
    System.out.println(output);
  }
}
