package sk.bednarik.nlp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.learning.impl.elements.SkipGram;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * This Class exists purely because DL4J implementation is complete and utter shit
 * Also there is lots of copy-paste
 *
 * @since 5.2.2019
 */
@Slf4j
public class Dl4jWord2VecUtils {

  private static final String WHITESPACE_REPLACEMENT = "_Az92_";

  public static Word2Vec readTextModel(@NonNull File file) throws IOException {
    if (!file.exists() || !file.isFile()) {
      throw new ND4JIllegalStateException("File [" + file.getAbsolutePath() + "] doesn't exist");
    }
    try (InputStream fis = new FileInputStream(file)) {
      return readTextModel(fis, file.getName());
    }
  }

  public static Word2Vec readTextModel(InputStream modelFileInputStream, String filename)
      throws IOException, NumberFormatException {
    InMemoryLookupTable lookupTable;
    VocabCache<VocabWord> cache;
    INDArray syn0;
    int words, size;

    int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();

    boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();

    if (originalPeriodic) {
      Nd4j.getMemoryManager().togglePeriodicGc(false);
    }
    Nd4j.getMemoryManager().setOccasionalGcFrequency(50000);

    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(GzipUtils.isCompressedFilename(filename)
            ? new GZIPInputStream(modelFileInputStream)
            : modelFileInputStream, "UTF-8"))) {
      String line = reader.readLine();
      String[] initial = line.split("\t");
      words = Integer.parseInt(initial[0]);
      size = Integer.parseInt(initial[1]);
      syn0 = Nd4j.create(words, size);
      cache = new AbstractCache<>();

      lookupTable = (InMemoryLookupTable<VocabWord>) new InMemoryLookupTable.Builder<VocabWord>()
          .cache(cache)
          .useHierarchicSoftmax(false).vectorLength(size).build();

      int currLine = 0;
      while ((line = reader.readLine()) != null) {
        //System.out.println(line);
        String[] split = line.split("\t");
        Preconditions.checkState(split.length == size + 1, "Expected %s values, got %s", size + 1,
            split.length);
        String word = split[0].replaceAll(WHITESPACE_REPLACEMENT, " ");

        float[] vector = new float[split.length - 1];
        for (int i = 1; i < split.length; i++) {
          vector[i - 1] = Float.parseFloat(split[i]);
        }
        syn0.putRow(currLine, Nd4j.create(vector));

        VocabWord vw = new VocabWord(1.0, word);
        vw.setIndex(cache.numWords());

        cache.addToken(vw);
        cache.addWordToIndex(vw.getIndex(), vw.getLabel());

        cache.putVocabWord(word);

        Nd4j.getMemoryManager().invokeGcOccasionally();

        currLine++;
      }

      lookupTable = (InMemoryLookupTable) new InMemoryLookupTable.Builder().cache(cache)
          .vectorLength(size)
          .build();

    } finally {
      if (originalPeriodic) {
        Nd4j.getMemoryManager().togglePeriodicGc(true);
      }

      Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
    }

    lookupTable.setSyn0(syn0);

    Word2Vec ret = new Word2Vec.Builder().useHierarchicSoftmax(false).resetModel(false)
        .layerSize(syn0.columns())
        .allowParallelTokenization(true).elementsLearningAlgorithm(new SkipGram<VocabWord>())
        .learningRate(0.025).windowSize(5).workers(1).build();

    ret.setVocab(cache);
    ret.setLookupTable(lookupTable);

    return ret;
  }

  public static Word2Vec readBinaryModel(File file) throws IOException {
    if (!file.exists() || !file.isFile()) {
      throw new ND4JIllegalStateException("File [" + file.getAbsolutePath() + "] doesn't exist");
    }
    try (InputStream fis = new FileInputStream(file)) {
      return readBinaryModel(fis, file.getName(), true, false);
    }
  }

  public static Word2Vec readBinaryModel(InputStream fileInputStream, String filename, boolean linebreaks,
      boolean normalize)
      throws NumberFormatException, IOException {
    InMemoryLookupTable<VocabWord> lookupTable;
    VocabCache<VocabWord> cache;
    INDArray syn0;
    int words, size;

    int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();
    boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();

    if (originalPeriodic) {
      Nd4j.getMemoryManager().togglePeriodicGc(false);
    }

    Nd4j.getMemoryManager().setOccasionalGcFrequency(50000);

    try (BufferedInputStream bis = new BufferedInputStream(GzipUtils.isCompressedFilename(filename)
        ? new GZIPInputStream(fileInputStream) : fileInputStream);
        DataInputStream dis = new DataInputStream(bis)) {
      words = Integer.parseInt(WordVectorSerializer.readString(dis));
      size = Integer.parseInt(WordVectorSerializer.readString(dis));
      syn0 = Nd4j.create(words, size);
      cache = new AbstractCache<>();

      lookupTable = (InMemoryLookupTable<VocabWord>) new InMemoryLookupTable.Builder<VocabWord>().cache(cache)
          .useHierarchicSoftmax(false).vectorLength(size).build();

      int cnt = 0;
      String word;
      float[] vector = new float[size];
      for (int i = 0; i < words; i++) {

        word = WordVectorSerializer.readString(dis);
        log.trace("Loading " + word + " with word " + i);

        for (int j = 0; j < size; j++) {
          vector[j] = WordVectorSerializer.readFloat(dis);
        }

        if (cache.containsWord(word)) {
          throw new ND4JIllegalStateException("Tried to add existing word. Probably time to switch linebreaks mode?");
        }

        syn0.putRow(i, normalize ? Transforms.unitVec(Nd4j.create(vector)) : Nd4j.create(vector));

        VocabWord vw = new VocabWord(1.0, word);
        vw.setIndex(cache.numWords());

        cache.addToken(vw);
        cache.addWordToIndex(vw.getIndex(), vw.getLabel());

        cache.putVocabWord(word);

        if (linebreaks) {
          dis.readByte(); // line break
        }

        Nd4j.getMemoryManager().invokeGcOccasionally();
      }
    } finally {
      if (originalPeriodic) {
        Nd4j.getMemoryManager().togglePeriodicGc(true);
      }

      Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
    }

    lookupTable.setSyn0(syn0);

    Word2Vec ret = new Word2Vec.Builder().useHierarchicSoftmax(false).resetModel(false).layerSize(syn0.columns())
        .allowParallelTokenization(true).elementsLearningAlgorithm(new SkipGram<VocabWord>())
        .learningRate(0.025).windowSize(5).workers(1).build();

    ret.setVocab(cache);
    ret.setLookupTable(lookupTable);

    return ret;

  }


}
