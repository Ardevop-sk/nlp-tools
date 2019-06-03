package sk.bednarik.nlp.tokenizer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.SanitizerService;
import sk.bednarik.nlp.utils.TokenizerService;

@Configuration
@Import({TokenizerService.class, SanitizerService.class})
public class TokenizerServiceConfig {

}
