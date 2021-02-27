package coden.smarttranslate.config;

import coden.multitran.crawler.MultitranCrawler;
import coden.multitran.translation.MultitranTranslationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    MultitranTranslationClient multitranClient(){
        return new MultitranCrawler();
    }
}
