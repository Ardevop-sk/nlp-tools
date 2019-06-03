package sk.bednarik.nlp;

import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.stereotype.Component;

@Component
public class Word2VecUtils {

  private static Word2Vec word2Vec;

  public static void init() throws IOException {
    initLemma();
  }

  public static void initFull() throws IOException {
    String filename = "sk.essentialdata.nlp/vectors/embeddings.txt.gz";
    word2Vec = Dl4jWord2VecUtils.readTextModel(
        Word2VecUtils.class.getClassLoader().getResourceAsStream(filename),
        filename);
  }

  public static void initLemma() throws IOException {
    String filename = "sk.essentialdata.nlp/vectors/vec-sk-skipgram-lemma.bin";
    word2Vec = Dl4jWord2VecUtils.readBinaryModel(
        Word2VecUtils.class.getClassLoader().getResourceAsStream(filename),
        filename, true, false);
  }

  public static Collection<String> wordsNearest(String word) {
    return wordsNearest(word, 10);
  }

  public static Collection<String> wordsNearest(String word, int top) {
    return word2Vec.wordsNearest(word, top);

  }

  public static Collection<String> analogy(String positiveToQuestion, String positiveToAnswer,
      String negativeToQuestion) {
    return analogy(positiveToQuestion, positiveToAnswer, negativeToQuestion, 10);
  }

  // wordPositive-wordNegative+wordPositive=?
  public static Collection<String> analogy(String positiveToQuestion, String positiveToAnswer,
      String negativeToQuestion, int top) {
    return word2Vec
        .wordsNearest(ImmutableList.of(positiveToAnswer, positiveToQuestion), ImmutableList.of(negativeToQuestion),
            top);
  }

  public static Collection<String> wordsNearest(List<String> positive, List<String> negative, int top) {
    return word2Vec.wordsNearest(positive, negative, top);
  }

  public static Map<String, Double> accuracy(List<String> questions) {
    return word2Vec.accuracy(questions);
  }
}
