package sk.bednarik.nlp.parser;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.AbstractTreebankLanguagePack;
import edu.stanford.nlp.trees.HeadFinder;
import edu.stanford.nlp.trees.ModCollinsHeadFinder;
import edu.stanford.nlp.trees.SemanticHeadFinder;
import edu.stanford.nlp.trees.UniversalSemanticHeadFinder;

public class SlovakTreebankLanguagePack extends AbstractTreebankLanguagePack {
    private static final String[] pennPunctTags = new String[]{"PUNCT"};
    private static final String[] pennSFPunctTags = new String[]{"PUNCT"};
    private static final String[] collinsPunctTags = new String[]{"\'\'", "``", ".", ":", ","};
    private static final String[] pennPunctWords = new String[]{"\'\'", "\'", "``", "`", "-LRB-", "-RRB-", "-LCB-", "-RCB-", ".", "?", "!", ",", ":", "-", "--", "...", ";"};
    private static final String[] pennSFPunctWords = new String[]{".", "!", "?"};
    private static final char[] annotationIntroducingChars = new char[]{'-', '=', '|', '#', '^', '~', '_', '['};
    private static final String[] pennStartSymbols = new String[]{"ROOT"};

    public String[] punctuationTags() {
        return pennPunctTags;
    }

    public String[] punctuationWords() {
        return pennPunctWords;
    }

    public String[] sentenceFinalPunctuationTags() {
        return pennSFPunctTags;
    }

    public String[] sentenceFinalPunctuationWords() {
        return pennSFPunctWords;
    }

    public String[] evalBIgnoredPunctuationTags() {
        return collinsPunctTags;
    }

    public char[] labelAnnotationIntroducingCharacters() {
        return annotationIntroducingChars;
    }

    public String[] startSymbols() {
        return pennStartSymbols;
    }

    @Override
    public String treebankFileExtension() {
        return "conllu";
    }

    @Override
    public HeadFinder headFinder() {
        return new ModCollinsHeadFinder(this);
    }

    @Override
    public HeadFinder typedDependencyHeadFinder() {
        return (HeadFinder)(this.generateOriginalDependencies?new SemanticHeadFinder(this, true):new UniversalSemanticHeadFinder(this, true));
    }

    public TokenizerFactory<CoreLabel> getTokenizerFactory() {
        return PTBTokenizer.coreLabelFactory();
    }

}
