package sk.bednarik.nlp.synonyms;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * The type Report controller.
 *
 */
public class SynonymsUtilsTest {

  @Test
  public void simpleTest() {
    SynonymsUtils synonymsUtils = new SynonymsUtils();
    synonymsUtils.init(SynonymsUtilsTest.class.getClassLoader().getResourceAsStream(
        "sk.essentialdata.nlp/wordnet/synonyms.txt"));

    Assert.assertEquals(Arrays.asList("auto", "automobil", "fáro", "kára", "vagón", "voz", "vozidlo"),
        Arrays.asList(synonymsUtils.findSynonyms("auto")));
    Assert.assertEquals(Arrays.asList("svojka"), Arrays.asList(synonymsUtils.findSynonyms("svojka")));
  }
}
