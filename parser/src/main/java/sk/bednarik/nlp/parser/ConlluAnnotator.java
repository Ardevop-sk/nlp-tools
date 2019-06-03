package sk.bednarik.nlp.parser;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class ConlluAnnotator implements Annotator {

  @Override
  public void annotate(Annotation annotation) {
    annotation.get(CoreAnnotations.SentencesAnnotation.class).stream()
        .map(sentence -> sentence
            .get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class))
        .forEach(sentenceGraph -> {
          sentenceGraph.edgeIterable().forEach(edge -> {
            edge.getTarget()
                .set(CoreAnnotations.CoNLLDepParentIndexAnnotation.class, edge.getSource().index());
            edge.getTarget().set(CoreAnnotations.CoNLLDepTypeAnnotation.class,
                edge.getRelation().getShortName());
          });
          sentenceGraph.getRoots().forEach(word -> {
            word.set(CoreAnnotations.CoNLLDepParentIndexAnnotation.class, 0);
            word.set(CoreAnnotations.CoNLLDepTypeAnnotation.class, "root");
          });
        });
  }

  @Override
  public Set<Class<? extends CoreAnnotation>> requires() {
    return new HashSet<>(Arrays.asList(
        CoreAnnotations.TextAnnotation.class,
        CoreAnnotations.IndexAnnotation.class,
        CoreAnnotations.ValueAnnotation.class,
        CoreAnnotations.TokensAnnotation.class,
        CoreAnnotations.SentencesAnnotation.class,
        CoreAnnotations.SentenceIndexAnnotation.class,
        CoreAnnotations.PartOfSpeechAnnotation.class,
        SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class,
        SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class,
        SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class
    ));
  }


  @Override
  public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
    return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
        CoreAnnotations.CoNLLDepParentIndexAnnotation.class,
        CoreAnnotations.CoNLLDepTypeAnnotation.class
    )));
  }
}
