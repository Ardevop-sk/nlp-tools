package sk.bednarik.nlp.stemmer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.StemmerService;

@Configuration
@Import({StemmerService.class})
public class StemmerServiceConfig {
}
