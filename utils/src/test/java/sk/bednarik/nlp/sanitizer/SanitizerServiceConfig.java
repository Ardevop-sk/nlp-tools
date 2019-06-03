package sk.bednarik.nlp.sanitizer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.SanitizerService;

@Configuration
@Import({SanitizerService.class})
public class SanitizerServiceConfig {

}
