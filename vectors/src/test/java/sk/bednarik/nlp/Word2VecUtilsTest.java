package sk.bednarik.nlp;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.groups.Tuple;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Slf4j
public class Word2VecUtilsTest {

  private Map<String, List<String>> questions = new ImmutableMap.Builder<String, List<String>>().put(
      "Capitals", Arrays.asList(
          "Francúzsko Paríž Nemecko Berlín"
      )).put(
      "MaleFemale", Arrays.asList(
          "muž kráľ žena kráľovná", "kráľ muž kráľovná žena"
      )).put(
      "Antonyms", Arrays.asList(
          "dobrý zlý pekný škaredý", "akceptovateľný neakceptovateľný istý neistý"
      )).put(
      "Currency", Arrays.asList(
          "Slovensko euro Česko koruna", "Slovensko euro Austrália dolár"
      )).put(
      "Family", Arrays.asList(
          "chlapec dievča brat sestra", "muž žena otec matka"
      )).put(
      "Comparative", Arrays.asList(
          "dobrý lepší malý menší"
      )).put(
      "Superlative", Arrays.asList(
          "dobrý najlepší malý najmenší", "pekný najkrajší špinavý najšpinavší"
      )).put(
      "Plurals", Arrays.asList(
          "okno okná auto autá", "lopta lopty noha nohy"
      )).put(
      "PastPresentFuture", Arrays.asList(
          "je bude ide pôjde", "kráčal kráča nosil nosí"
      )).build();

  private static boolean caseInsensitiveModel;

  @BeforeClass
  public static void init() throws IOException {
    // Analogy: Total accuracy = 50.0%
    // Synonyms: Total Accuracy: 13.011588675370398%
    /*Word2VecUtils.initFull();
    caseInsensitiveModel = true;*/

    // Analogy: Total accuracy = 31.25%
    // Synonyms: Total Accuracy: 6.087721871791111%
    Word2VecUtils.initLemma();
    caseInsensitiveModel = false;
  }

  @Test
  public void testNearestWords() throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(Word2VecUtilsTest.class.getClassLoader()
        .getResourceAsStream("sk.essentialdata.nlp/wordnet/synonyms.txt"), "UTF-8"))) {
      long totalWords = 0;
      long totalCount = 0;
      String line;
      while ((line = br.readLine()) != null) {
        List<String> words = Arrays.asList(line.split(", "));
        String query = words.get(0);
        if (!query.contains(" ")) {
          Collection<String> predictedSynonyms = Word2VecUtils.wordsNearest(query, 10);
          HashSet<String> predictions = new HashSet<>(predictedSynonyms);
          long count = words.stream().filter(word -> !word.contains(" ")).filter(word -> predictions.contains(word))
              .count();
          long size = words.stream().filter(word -> !word.contains(" ")).count();
          log.trace("Finding synonyms for: " + query);
          log.trace("Expected: " + line);
          log.trace("Got: " + String.join(",", predictedSynonyms));
          log.trace("Hits: " + count);
          log.trace("Accuracy: " + (double) count / (double) size * 100 + "%");
          totalWords += size;
          totalCount += count;
        } else {
          log.trace("Skipping phrase: " + query);
        }
      }
      double totalAccuracy = (double) totalCount / (double) totalWords * 100;
      log.info("Total Accuracy: " + totalAccuracy + "%");
      assertThat(totalAccuracy).isGreaterThan(5); //TODO: Improve model/test, increase requirements
    }
  }

  @Test
  public void testAnalogyWords() throws IOException {

    int totalCorrect = 0;
    int totalSize = 0;
    for (Entry<String, List<String>> entry : questions.entrySet()) {
      int correct = 0;
      int size = 0;
      log.info(entry.getKey() + ":");
      for (String question : entry.getValue()) {
        if (caseInsensitiveModel) {
          question = question.toLowerCase();
        }
        String[] split = question.split(" ");
        log.info(
            "Question: " + split[2] + "-" + split[0] + "+" + split[1] + "= ?");
        Collection<String> answer = Word2VecUtils.analogy(split[2], split[1], split[0], 5);
        log.info("Expected: " + split[3] + " Got: " + String.join(",", answer));
        Optional<String> predictedAnswer = answer.stream().findFirst();
        if (predictedAnswer.isPresent() && split[3].equals(predictedAnswer.get())) {
          correct++;
          log.info("Correct!");
        } else {
          if (predictedAnswer.isPresent()) {
            if (answer.stream().anyMatch(oneAnswer -> oneAnswer.equals(split[3]))) {
              log.info("Incorrect, but in TOP 5!");
            } else {
              log.info("Incorrect!");
            }
          } else {
            log.info("Not in vocabulary!");
          }
        }
        size++;
      }
      log.info(entry.getKey() + " accuracy = " + ((double) correct / (double) size) * (double) 100 + "%");
      totalCorrect += correct;
      totalSize += size;
    }
    double totalAccuracy =((double) totalCorrect / (double) totalSize) * (double) 100;
    log.info("Total accuracy = " + totalAccuracy + "%");
    assertThat(totalAccuracy).isGreaterThan(25); //TODO: Improve model, add more tests, increase requirements
  }

}
