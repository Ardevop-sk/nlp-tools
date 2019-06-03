package sk.bednarik.nlp.ner;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.NERService;

@Configuration
@Import({NERService.class})
public class NERServiceConfig {

}
