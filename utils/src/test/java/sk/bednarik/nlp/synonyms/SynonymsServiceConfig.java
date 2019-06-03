package sk.bednarik.nlp.synonyms;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.SynonymsService;

@Configuration
@Import({SynonymsService.class})
public class SynonymsServiceConfig {

}
