package sk.bednarik.nlp.lemmatizer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.LemmaService;

@Configuration
@Import({LemmaService.class})
public class LemmaServiceConfig {

}
