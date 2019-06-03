package sk.bednarik.nlp.ner;

public class TokenTag {
    private int begin;
    private int end;
    private String label;

    public TokenTag(int begin, int end, String label) {
        this.begin = begin;
        this.end = end;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }
}
