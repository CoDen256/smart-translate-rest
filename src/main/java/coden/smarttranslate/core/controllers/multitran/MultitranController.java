package coden.smarttranslate.core.controllers.multitran;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/multitran")
public class MultitranController {

    private final MultitranCrawler crawler;

    public MultitranController() {
        crawler = new MultitranCrawler();
    }
    // TODO: validation, inaccessibility of multitran
    @PostMapping(value="/translate", produces = "application/json")
    public MultitranResponse translate(@RequestBody MultitranRequest payload){
        String url = crawler.getUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
        List<String> translations = crawler.parseTranslations(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());

        MultitranResponse response = new MultitranResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setTranslatedPhrase(translations);
        response.setUrl(url);

        return response;
    }
}
