package sk.bednarik.nlp.tokenizer.model;

public class Token {

  private String type;
  private String text;
  private Offset offset;

  public Token(String type, String text, Offset offset) {
    this.type = type;
    this.text = text;
    this.offset = offset;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Offset getOffset() {
    return offset;
  }

  public void setOffset(Offset offset) {
    this.offset = offset;
  }
}
