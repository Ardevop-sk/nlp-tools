package sk.bednarik.nlp.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.Util;
import sk.bednarik.nlp.stemmer.SlovakStemmer;

/**
 * @author miso
 * @date 4/29/14.
 */
public class WordRootTokenFilter extends TokenFilter {
        private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);
    private final KeywordAttribute keywordAtt = addAttribute(KeywordAttribute.class);
    private List<String> buffer;
    private State savedState;
    private FST<CharsRef> fst;
    private boolean useStemmer;
    private SlovakStemmer stemmer;

    /**
     * Construct a token stream filtering the given input.
     *
     * @param input
     */
    WordRootTokenFilter(TokenStream input, FST<CharsRef> fstFile, boolean useStemmer) throws IOException {
        super(input);
        this.fst = fstFile;
        this.useStemmer = useStemmer;
        this.stemmer = new SlovakStemmer();
    }

    /**
     * 1. Read next token
     * 2. Apply stemmer
     * 3. If there are multiple stems
     * just one increment take place and other stems are put into the buffer
     * the buffer and state of the attributes are saved
     * 4. In the next calls, if the buffer is not empty, the stemmer is not called,
     * but the value from buffer is used (@var nextStem)
     * {@inheritDoc}
     */
    @Override
    public boolean incrementToken() throws IOException {
        if (buffer != null && !buffer.isEmpty()) {
            String nextStem = buffer.remove(0);
            restoreState(savedState);
            posIncAtt.setPositionIncrement(0);
            termAtt.copyBuffer(nextStem.toCharArray(), 0, nextStem.length());
            return true;
        }

        if (!input.incrementToken()) {
            return false;
        }

        if (keywordAtt.isKeyword()) {
            return true;
        }

        // this can surely be optimized, but we have no need yet
        // @see UnicodeUtil::UTF16toUTF8() which adds a lot of zero bytes at the end.

        int length = termAtt.length();

        /**
         * @var termBuffer input buffer, which is being overwritten from the start by the next word.
         */
        char[] termBuffer = termAtt.buffer();

        /**
         * @var bytesRef wrapper for byte array, which is an input for FST
         */
        BytesRef bytesRef = new BytesRef(new String(termBuffer).substring(0, length).getBytes("UTF-8"));
        /**
         * @var charsRef wrapper for char array, which is an output from FST.
         * In case of multiple outputs, they are separated by "|"
         */
        CharsRef charsRef = Util.get(fst, bytesRef);
//        charsRef = utilGetDebug(fst, bytesRef);

        if (charsRef == null) {
            if (useStemmer) {
                final int newlen = stemmer.stem(termAtt.buffer(), termAtt.length());
                termAtt.setLength(newlen);
            }
            return true; // not found in FST
        }
        buffer = new ArrayList<String>(Arrays.asList(charsRef.toString().split("\\|")));

        if (buffer.isEmpty()) { // we do not know this word, return it unchanged
            return true;
        }

        String stem = buffer.remove(0);
        termAtt.copyBuffer(stem.toCharArray(), 0, stem.length());

        if (!buffer.isEmpty()) {
            savedState = captureState();
        }

        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() throws IOException {
        super.reset();
        buffer = null;
    }
}
