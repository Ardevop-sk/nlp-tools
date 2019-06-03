package sk.bednarik.nlp.parser;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.ParserService;

@Configuration
@Import({ParserService.class})
public class ParserServiceConfig {

}
