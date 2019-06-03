package sk.bednarik.nlp.sanitizer;

import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utilita dokáže odstrániť nadbytočné medzery medzi písmenami, ktoré sa používajú na zvýraznenie Využíva pritom
 * jednudchú heuristiku. Keď všetky slová, ktoré sú takto zvýraznené musia mať minimálne dve písmená, ak takéto slovo
 * existuje nesmie byť zo slovníka stopKeys
 *
 */
public class SpaceHighlightingSanitizer {
  //TODO: Add more tests and validate regexps

  private static String blankCharacter = "[\\s\\p{Z}]";
  private static Pattern matchingPattern = Pattern
      .compile("(" + blankCharacter + "|\\A)(\\w[\\h])+\\w(" + blankCharacter + "|\\Z)",
          Pattern.UNICODE_CHARACTER_CLASS);
  private static HashSet<String> stopKeys = Sets
      .newHashSet("a v", "a s", "a u", "a o", "a k", "a z", "i s", "i o", "i u", "i v", "i k", "i z");

  public static String sanitize(String input) {
    Matcher matcher = matchingPattern.matcher(input);
    HashMap<String, String> replaces = new HashMap<>();
    while (matcher.find()) {
      String key = matcher.group(0).replaceAll(blankCharacter + "$", "").replaceAll("^" + blankCharacter, "");
      if (!stopKeys.contains(key.toLowerCase().replaceAll("\\h", " "))) {
        String value = matcher.group(0).replaceAll("\\h", "");
        //If key is not empty and makes sense add to replaces, ignores numbers
        if (key.matches("[\\p{L}\\h]+")) {
          replaces.put(key, value);
        }
      }
    }
    String output = input;

    List<Map.Entry<String, String>> entries = replaces
        .entrySet()
        .stream()
        .sorted((entry1, entry2) -> entry2.getKey().length() - entry1.getKey().length())
        .collect(Collectors.toList());

    for (Map.Entry<String, String> entry : entries) {
      output = output.replace(entry.getKey(), entry.getValue());
    }
    //Replace multiple different spaces with just one
    output = output.replaceAll("[\\p{Zs}]{2,}", " ");
    return output;
  }
}
