package sk.bednarik.nlp.ner;

import com.google.common.collect.Sets;
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.util.ArraySet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleNERAnnotator implements Annotator {

  private int maxTokens = 0;
  private HashMap<String, String> annotations = new HashMap<>();
  private HashSet<String> addable;

  public SimpleNERAnnotator(File sourceFile, String... addable) throws IOException {
    this(new FileReader(sourceFile), Sets.newHashSet(addable));
  }

  public SimpleNERAnnotator(InputStream sourceFile, String... addable) throws IOException {
    this(sourceFile, Sets.newHashSet(addable));
  }

  public SimpleNERAnnotator(InputStream inputStream, HashSet<String> addable) throws IOException {
    this(new InputStreamReader(inputStream), addable);
  }

  public SimpleNERAnnotator(Reader sourceFileReader, HashSet<String> addable) throws IOException {
    this.addable = addable;
    try (BufferedReader br = new BufferedReader(sourceFileReader)) {
      br.lines()
          .map(line -> line.split("\t"))
          .forEach(stringAndClass -> {
            Annotation annotation = new Annotation(stringAndClass[0]);
            new TokenizerAnnotator()
                .annotate(annotation); //TODO: must use the same tokenizer as pipeline!!!
            List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);
            //Find line with the largest amount of tokens
            if (maxTokens < tokens.size()) {
              maxTokens = tokens.size();
            }
            annotations.put(stringAndClass[0], stringAndClass[1]);
          });
    }
  }

  public void annotate(Annotation annotation) {
    List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);
    String originalText = annotation.get(CoreAnnotations.TextAnnotation.class);
    int tokenNumber = 0;
    // For each token
    for (CoreLabel token : tokens) {
      // Peek each token but maximum of maxTokens forward
      for (int i = 0; i < maxTokens; i++) {
        // Check for overflow for peek
        if ((tokenNumber + i) < tokens.size()) {
          // Extract the full string from original text from start of current token
          // to the end of the peeked token
          String key = originalText
              .substring(token.beginPosition(), tokens.get(tokenNumber + i).endPosition());
          // Check if annotations map contains the full string
          if (annotations.containsKey(key)) {
            // Go from first token to the last token in peek
            for (int j = 0; j <= i; j++) {
              CoreLabel token1 = tokens.get(tokenNumber + j);
              // Annotate the NER class based on map
              if (token1.ner() == null || token1.ner().equals("O")) {
                token1.setNER(annotations.get(key));
                // If the NER classes are concatenable do concat
              } else if (addable.contains(token1.ner())) {
                token1.setNER(token1.ner() + "," + annotations.get(key));
              }
            }
          }
        }
      }
      // Increment the number of current token
      tokenNumber++;
    }

    //TODO: think how to do things with CoreNLP immutable way
    tokens.stream()
        .filter(token -> token.ner() == null)
        .forEach(token -> token.setNER("O"));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.CharacterOffsetBeginAnnotation.class,
        CoreAnnotations.CharacterOffsetEndAnnotation.class
    )));
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.emptySet();
  }
}
