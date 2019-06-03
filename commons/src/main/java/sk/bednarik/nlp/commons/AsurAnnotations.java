package sk.bednarik.nlp.commons;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.ErasureUtils;

import java.util.List;

public class AsurAnnotations {

  public static class ScrubbedTextAnnotation implements CoreAnnotation<String> {

    public Class<String> getType() {
      return String.class;
    }
  }

  public static class OriginalCharacterOffsetBeginAnnotation implements CoreAnnotation<Integer> {

    public Class<Integer> getType() {
      return Integer.class;
    }
  }

  public static class OriginalCharacterOffsetEndAnnotation implements CoreAnnotation<Integer> {

    public Class<Integer> getType() {
      return Integer.class;
    }
  }

  public static class PhraseAnnotation implements CoreAnnotation<List<CoreMap>> {

    public Class<List<CoreMap>> getType() {
      return ErasureUtils.uncheckedCast(List.class);
    }
  }

  public static class Synonyms implements CoreAnnotation<List<String>> {

    public Class<List<String>> getType() {
      return ErasureUtils.uncheckedCast(List.class);
    }
  }
}
