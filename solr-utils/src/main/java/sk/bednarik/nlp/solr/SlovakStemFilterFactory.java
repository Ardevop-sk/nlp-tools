package sk.bednarik.nlp.solr;

import java.util.Map;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

public class SlovakStemFilterFactory extends TokenFilterFactory {
  
  /** Creates a new SlovakStemFilterFactory */
  public SlovakStemFilterFactory(Map<String, String> args) {
    super(args);
    if (!args.isEmpty()) {
      throw new IllegalArgumentException("Unknown parameters: " + args);
    }
  }
  
  @Override
  public TokenStream create(TokenStream input) {
    return new SlovakStemFilter(input);
  }
}
