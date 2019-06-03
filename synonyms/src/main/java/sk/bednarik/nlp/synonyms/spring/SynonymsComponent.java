package sk.bednarik.nlp.synonyms.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.InputStream;
import sk.bednarik.nlp.commons.AnnComponent;
import sk.bednarik.nlp.synonyms.SynonymsAnnotator;

public class SynonymsComponent extends AnnComponent {

  @Override
  protected Annotator prepareAnnotator() {
    InputStream inputStreamSynonyms = SynonymsComponent.class.getClassLoader().getResourceAsStream(
        "sk.essentialdata.nlp/wordnet/synonyms.txt");
    InputStream inputStreamStopWords = SynonymsComponent.class.getClassLoader()
        .getResourceAsStream("sk.essentialdata.nlp/lemma/stopwords_file.txt");
    return new SynonymsAnnotator(inputStreamSynonyms, inputStreamStopWords);
  }
}
