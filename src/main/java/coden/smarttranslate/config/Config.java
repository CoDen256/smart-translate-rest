package coden.smarttranslate.config;

import coden.multitran.crawler.MultitranCrawler;
import coden.multitran.crawler.MultitranDocumentFetcher;
import coden.multitran.translation.MultitranTranslationClient;
import coden.reverso.ReversoClient;
import coden.reverso.client.api.ReversoApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableAutoConfiguration
public class Config {

    @Bean
    MultitranTranslationClient multitranClient(MultitranDocumentFetcher documentFetcher) {
        return new MultitranCrawler(documentFetcher);
    }

    @Bean
    ReversoClient reversoClient(@Qualifier("api") WebClient webClient) {
        return new ReversoApi(webClient);
    }
}
