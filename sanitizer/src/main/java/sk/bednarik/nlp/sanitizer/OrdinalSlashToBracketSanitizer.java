package sk.bednarik.nlp.sanitizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdinalSlashToBracketSanitizer {

  private static final Pattern ordinalWithSlash = Pattern.compile("[.\\s\\p{Z}][A-Za-z]/");

  public static String sanitize(String input) {
    Matcher matcher = ordinalWithSlash.matcher(input);
    while (matcher.find()) {
      String key = matcher.group(0);
      String value = matcher.group(0).replace("/", ")");
      input = input.replace(key, value);
    }
    return input;
  }
}