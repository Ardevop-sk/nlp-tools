package sk.bednarik.nlp.sanitizer;

/**
 *
 * Basic normalizer for slovak diacritics in OCRed text
 */
public class OcrTextNormalizer {

  public String sanitize(String input) {
    input = input.replace("l'", "ľ");
    input = input.replace("d'", "ď");
    input = input.replace("i'", "í");
    input = input.replace("t'", "ť");

    input = input.replaceAll("\\.{4,}", "");
    input = input.replaceAll("-{2,}", "");
    input = input.replaceAll("_{2,}", "");
    input = input.replaceAll("\\n{2,}", "\\n");

    return input;
  }

}
