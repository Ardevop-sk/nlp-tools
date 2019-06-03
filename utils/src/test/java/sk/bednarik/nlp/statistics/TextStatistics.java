package sk.bednarik.nlp.statistics;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import jdk.nashorn.internal.runtime.options.Options;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bednarik.nlp.lemmatizer.LemmaServiceConfig;
import sk.bednarik.nlp.utils.LemmaService;

/**
 * @author Pavol Berta
 * @since 1. 3. 2019
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LemmaServiceConfig.class})
public class TextStatistics {

  private static final Pattern pattern = Pattern.compile("\\d+|[.,:\"“„;§'()]|[A-Za-z]\\)");
  private static final String filename_300_2005 = "2005_300.txt";
  private static final String filename_301_2005 = "2005_301.txt";

  @Autowired
  private LemmaService lemmaService;

  private static String readFile() throws IOException {
    return IOUtils.toString(
        Objects.requireNonNull(TextStatistics.class.getClassLoader().getResourceAsStream(filename_301_2005)),
        StandardCharsets.UTF_8);
  }

  @Test
  public void countAllTokens() throws IOException {
    String input = readFile();

    List<CoreMap> labels = lemmaService.lemmatizeKeepNotLemmatized(input);
    MapCounter countTokens = new MapCounter();
    MapCounter countLemmas = new MapCounter();
    MapCounter countNotLemmasAll = new MapCounter();
    MapCounter countNotLemmas = new MapCounter();
    List<String> output = labels
        .stream()
        .map(sentences -> sentences.get(CoreAnnotations.TokensAnnotation.class))
        .flatMap(Collection::stream)
        .map(token -> {
          countTokens.add(token.word().toLowerCase());
          countLemmas.add(Optional.ofNullable(token.lemma()).orElse(""));
          return token;
        })
        .filter(coreLabel -> coreLabel.lemma() == null)
        .map(c -> {
          countNotLemmasAll.add(c.word().toLowerCase());
          return c;
        })
        .filter(c -> !pattern.matcher(c.word()).find())
        .map(t -> {
          countNotLemmas.add(t.originalText().toLowerCase());
          return t.originalText().toLowerCase();
        })
        .collect(Collectors.toList());


    System.out.println("Tokens: " + countTokens.getSortedByValue());
    System.out.println("Lemmas: " + countLemmas.getSortedByValue());
    System.out.println("NotLemmasAll: " + countNotLemmasAll.getSortedByValue());
    System.out.println("NotLemmas: " + countNotLemmas.getSortedByValue());
  }
}
