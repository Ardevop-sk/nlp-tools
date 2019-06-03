package sk.bednarik.nlp.spring;

import edu.stanford.nlp.pipeline.Annotator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import sk.bednarik.nlp.LemmaAnnotator;
import sk.bednarik.nlp.commons.AnnComponent;

/**
 * @author Pavol Berta
 * @since 21. 5. 2019
 */
public abstract class AbstractFSTLemmaComponent extends AnnComponent {

  /**
   * @param keepOriginal if lemma is not found keep original word
   */
  protected Annotator prepareAnnotator(boolean keepOriginal) {
    try (InputStream inputStream = FSTLemmaComponent.class.getClassLoader()
        .getResourceAsStream("sk.essentialdata.nlp/lemma/sk-lemma-data.fst")) {
      Properties properties = new Properties();
      properties.setProperty("keepOriginal", String.valueOf(keepOriginal));
      return new LemmaAnnotator(inputStream, properties);
    } catch (IOException e) {
      //TODO:Handle exception
      e.printStackTrace();
    }
    return null;
  }
}
