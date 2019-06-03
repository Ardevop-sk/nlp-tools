package sk.bednarik.nlp.utils.model;


import sk.bednarik.nlp.tokenizer.model.Token;

import java.util.Arrays;
import java.util.List;

public class Lemma {

  private Token token;
  private List<String> lemmas;

  public Lemma(Token token, String[] lemmas) {
    this.token = token;
    this.lemmas = Arrays.asList(lemmas);
  }

  public List<String> getLemmas() {
    return lemmas;
  }

  public void setLemmas(List<String> lemmas) {
    this.lemmas = lemmas;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }
}
