package sk.bednarik.nlp.ner;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.ArraySet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class StackedNamedEntityTagAnnotator implements Annotator {
    @Override
    public void annotate(Annotation annotation) {
        annotation.get(CoreAnnotations.TokensAnnotation.class)
                .forEach(label -> label.set(
                        CoreAnnotations.StackedNamedEntityTagAnnotation.class,
                        label.get(CoreAnnotations.NamedEntityTagAnnotation.class)
                ));
    }

    @Override
    public Set<Class<? extends CoreAnnotation>> requires() {
        return Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(
                CoreAnnotations.TextAnnotation.class,
                CoreAnnotations.TokensAnnotation.class,
                CoreAnnotations.SentencesAnnotation.class,
                CoreAnnotations.NamedEntityTagAnnotation.class
        )));
    }

    @Override
    public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
        return Collections.emptySet();
    }
}
