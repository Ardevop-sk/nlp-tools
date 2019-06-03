package sk.bednarik.nlp.synonyms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;

public class SynonymsUtils {

  private HashMap<String, String[]> slovnik = new HashMap<>();

  public void init(InputStream file) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file, "UTF-8"))) {
      br.lines()
          .map(line -> line.split(", "))
          .forEach(parts -> Stream.of(parts)
              .forEach(part -> slovnik.computeIfAbsent(part, key -> parts)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String[] findSynonyms(String term) {
    return slovnik.getOrDefault(term, new String[]{term});
  }
}
