package sk.bednarik.nlp.tagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.TaggerService;

@Configuration
@Import({TaggerService.class})
public class TaggerServiceConfig {

}
