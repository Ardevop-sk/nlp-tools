package sk.bednarik.nlp.ssplit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.SSplitService;
import sk.bednarik.nlp.utils.SanitizerService;

@Configuration
@Import({SSplitService.class, SanitizerService.class})
public class SSplitServiceConfig {

}
