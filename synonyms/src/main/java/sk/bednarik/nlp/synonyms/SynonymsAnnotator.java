package sk.bednarik.nlp.synonyms;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.ArrayCoreMap;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.logging.Redwood;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sk.bednarik.nlp.commons.AsurAnnotations;

public class SynonymsAnnotator implements Annotator {

  private HashSet<String> stopWords = new HashSet<>();
  private HashMap<String, List<List<String>>> slovnik = new HashMap<>();
  private HashMap<String, Set<String>> index = new HashMap<>();

  private HashMap<String, String[]> interchangeableWords = new HashMap<String, String[]>() {{
    put("sa", new String[]{"si"});
    put("si", new String[]{"sa"});
  }};

  private static Redwood.RedwoodChannels log = Redwood.channels(SynonymsAnnotator.class);

  //TODO: Improve performance
  public SynonymsAnnotator(InputStream synonymsFile, InputStream stopWordsFile) {
    prepareStopWords(stopWordsFile);
    prepareSynonyms(synonymsFile);
  }

  private void prepareStopWords(InputStream stopWordsFile) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(stopWordsFile, "UTF-8"))) {
      br.lines()
          .forEach(word -> stopWords.add(word));
    } catch (IOException e) {
      log.error(e);
    }
  }

  private void prepareSynonyms(InputStream synonymsFile) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(synonymsFile, "UTF-8"))) {
      br.lines()
          .map(line -> line.split(", "))
          .forEach(phrases -> Stream.of(phrases)
              .peek(phrase -> {
                String[] words = phrase.split(" ");
                for (String word : words) {
                  if (!stopWords.contains(word)) {
                    index.compute(word, (key, value) -> {
                      if (value == null) {
                        value = new HashSet<>();
                      }
                      value.add(phrase);
                      return value;
                    });
                  }
                }
              })
              .forEach(phrase -> slovnik.compute(phrase, (key, value) -> {
                if (value == null) {
                  value = new ArrayList<>();
                }
                value.add(Arrays.asList(phrases));
                return value;
              })));
    } catch (IOException e) {
      log.error(e);
    }
  }

  @Override
  public void annotate(Annotation annotation) {
    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      List<CoreMap> phrases = new ArrayList<>();
      SemanticGraph sg = sentence.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class);
      for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
        if (!stopWords.contains(token.lemma().toLowerCase())) {
          IndexedWord tokenWord = new IndexedWord(token);
          Set<IndexedWord> children = sg.getChildren(tokenWord);
          HashMap<String, IndexedWord> childrenLemmas = new HashMap<>();
          for (IndexedWord word : children) {
            childrenLemmas.put(word.lemma().toLowerCase(), word);
            childrenLemmas.put(word.originalText().toLowerCase(), word);
            for (String interchange : interchangeableWords.getOrDefault(word.originalText(), new String[0])) {
              childrenLemmas.put(interchange, word);
            }
          }
          childrenLemmas.put(token.lemma().toLowerCase(), tokenWord);
          childrenLemmas.put(token.originalText().toLowerCase(), tokenWord);

          Set<String> phrasesStrings = index.getOrDefault(token.lemma().toLowerCase(), new HashSet<>());
          phrasesStrings.addAll(index.getOrDefault(token.originalText().toLowerCase(), new HashSet<>()));

          for (String phraseString : phrasesStrings) {
            String[] words = phraseString.split(" ");
            boolean finished = true;
            List<CoreLabel> phraseWords = new ArrayList<>();
            for (String word : words) {
              if (!childrenLemmas.containsKey(word)) {
                finished = false;
                break;
              } else {
                phraseWords.add(childrenLemmas.get(word).backingLabel());
              }
            }
            if (finished) {
              List<List<String>> synonyms = slovnik.get(phraseString);
              List<String> flatSynonyms =
                  synonyms.stream()
                      .flatMap(List::stream)
                      .collect(Collectors.toList());
              CoreMap phrase = new ArrayCoreMap();
              phrase.set(AsurAnnotations.Synonyms.class, flatSynonyms);
              phrase.set(CoreAnnotations.TokensAnnotation.class, phraseWords);
              phrases.add(phrase);
            }
          }
        }

      }
      sentence.set(AsurAnnotations.PhraseAnnotation.class, phrases);
    }
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        AsurAnnotations.PhraseAnnotation.class,
        AsurAnnotations.Synonyms.class
    )));
  }


  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return new HashSet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.IndexAnnotation.class,
        CoreAnnotations.ValueAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.LemmaAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class,
        CoreAnnotations.SentenceIndexAnnotation.class,
        CoreAnnotations.PartOfSpeechAnnotation.class,
        SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class,
        SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class,
        SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class
    ));
  }
}
