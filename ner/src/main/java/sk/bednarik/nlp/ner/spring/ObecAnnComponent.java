package sk.bednarik.nlp.ner.spring;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class ObecAnnComponent extends SimpleNERAnnComponent {

  @Override
  protected Pair<String, String[]> configure() {
    return new ImmutablePair<>("sk.essentialdata.nlp/ner/gazzette/obec.txt", new String[]{"MENO", "PRIEZVISKO"});
  }
}
