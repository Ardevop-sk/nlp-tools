package sk.bednarik.nlp.tokenizer;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

  public static void main(String[] args) throws IOException {
    tokenizeCoreNLP("sourceData.txt");
  }

  public static List<String> tokenizeCoreNLP(String input) throws IOException {
    // option #2: By token
    PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<>(new StringReader(input),
        new CoreLabelTokenFactory(), "");
    List<String> tokens = new ArrayList<>();
    while (ptbt.hasNext()) {
      CoreLabel label = ptbt.next();
      tokens.add(label.toString());
    }
    return tokens;
  }

}
