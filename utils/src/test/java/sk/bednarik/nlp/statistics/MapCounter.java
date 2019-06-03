package sk.bednarik.nlp.statistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Pavol Berta
 * @since 27. 2. 2019
 */
public class MapCounter {


  private final Map<String, Integer> tokens = new TreeMap<>();

  public void add(String token) {
    this.tokens.merge(token.toLowerCase(), 1, (a, b) -> a + b);
  }

  public Set<String> getAllTokens() {
    return this.tokens.keySet();
  }

  public Integer getCount(String token) {
    return this.tokens.get(token);
  }

  public String toString() {
    return "" + this.tokens.toString();
  }

  public void removeToken(String token) {
    this.tokens.remove(token);
  }

  public Map<String, Integer> getSortedByValue() {
    List<Entry<String, Integer>> list = new ArrayList<>(tokens.entrySet());
    list.sort(Entry.comparingByValue());

    Map<String, Integer> result = new LinkedHashMap<>();
    for (Entry<String, Integer> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }
}
