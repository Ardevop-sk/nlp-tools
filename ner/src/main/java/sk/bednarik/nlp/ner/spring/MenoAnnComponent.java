package sk.bednarik.nlp.ner.spring;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MenoAnnComponent extends SimpleNERAnnComponent {

  @Override
  protected Pair<String, String[]> configure() {
    return new ImmutablePair<>("sk.essentialdata.nlp/ner/gazzette/meno.txt", new String[0]);
  }
}
