package sk.bednarik.nlp.solr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoaderAware;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.apache.lucene.store.InputStreamDataInput;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.fst.CharSequenceOutputs;
import org.apache.lucene.util.fst.FST;

public class WordRootTokenFilterFactory extends TokenFilterFactory implements ResourceLoaderAware {
    private boolean useStemmer;
    private FST<CharsRef> fst;

    public static final String PARAM_DICTIONARY = "fst";
    public static final String PARAM_STEMMER = "useStemmer";

    /**
     * Initialize this factory via a set of key-value pairs.
     */
    public WordRootTokenFilterFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        try {
            return new WordRootTokenFilter(tokenStream, fst, useStemmer);
        } catch (IOException e) {
            RuntimeException runtimeException = new RuntimeException(e.getMessage(), e);
            runtimeException.setStackTrace(e.getStackTrace());
            throw runtimeException;
        }
    }

    @Override
    public void inform(ResourceLoader loader) throws IOException {
        String dictionaryArg = getOriginalArgs().get(PARAM_DICTIONARY);
        if (dictionaryArg == null) {
            throw new IllegalArgumentException("Parameter " + PARAM_DICTIONARY + " is mandatory.");
        }
        InputStream is = loader.openResource(dictionaryArg);
        Throwable var3 = null;
        try {
            fst = new FST(new InputStreamDataInput(new BufferedInputStream(is)), CharSequenceOutputs.getSingleton());
        } catch (Throwable var13) {
            var3 = var13;
            throw var13;
        } finally {
            if (is != null) {
                if (var3 != null) {
                    try {
                        is.close();
                    } catch (Throwable var12) {
                        var3.addSuppressed(var12);
                    }
                } else {
                    is.close();
                }
            }

        }
        useStemmer = ((getOriginalArgs().get(PARAM_STEMMER) != null) && (getOriginalArgs().get(PARAM_STEMMER).equals("true")));
    }

}
