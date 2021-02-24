package coden.smarttranslate.core.controllers.multitran;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/multitran")
public class MultitranController {

    private final MultitranCrawler crawler;

    public MultitranController() {
        crawler = new MultitranCrawler();
    }

    /**
     * Returns {@link MultitranTranslationResponse} containing list of translations, that were parsed
     * from www.multitran.com.
     * Following Status Codes could be returned by calling this request:
     *  1) 100 - OK, contains list of translations (may be empty)
     *  2) 400 - Bad Request, if {@link MultitranTranslationRequest} does not contain necessary information
     *  3) 503 - Service Unavailable, if www.multitran.com cannot be accessed for some reason.
     *
     * @param payload the {@link MultitranTranslationRequest} that holds request to translating
     * @return the {@link MultitranTranslationResponse} containing translations
     */
    @PostMapping(value="/translate", produces = "application/json")
    public ResponseEntity<MultitranTranslationResponse> translate(@RequestBody MultitranTranslationRequest payload){
        String url = crawler.getUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());

        MultitranTranslationResponse response = createMultitranTranslationResponse(payload, url);
        try {
            List<String> translations = crawler.parseTranslations(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
            response.setTranslations(translations);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    private MultitranTranslationResponse createMultitranTranslationResponse(MultitranTranslationRequest payload, String url) {
        MultitranTranslationResponse response = new MultitranTranslationResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(url);
        return response;
    }
}
