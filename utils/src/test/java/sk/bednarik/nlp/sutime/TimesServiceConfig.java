package sk.bednarik.nlp.sutime;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import sk.bednarik.nlp.utils.TimesService;

@Configuration
@Import({TimesService.class})
public class TimesServiceConfig {

}
