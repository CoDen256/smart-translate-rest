package coden.smarttranslate.controllers.multitran;

import coden.multitran.translation.MultitranTranslation;
import coden.multitran.translation.MultitranTranslationClient;
import coden.smarttranslate.controllers.multitran.translation.MultitranTranslationRequest;
import coden.smarttranslate.controllers.multitran.translation.MultitranTranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/multitran")
public class MultitranController {

    @Autowired
    private MultitranTranslationClient client;

    /**
     * Returns {@link MultitranTranslationResponse} containing list of translations, that were parsed
     * from www.multitran.com.
     * Following Status Codes could be returned by calling this request:
     * 1) 100 - OK, contains list of translations (may be empty)
     * 2) 400 - Bad Request, if {@link MultitranTranslationRequest} does not contain necessary information
     * 3) 503 - Service Unavailable, if www.multitran.com cannot be accessed for some reason.
     *
     * @param body
     *         the {@link MultitranTranslationRequest} that holds request to translating
     * @return the {@link MultitranTranslationResponse} containing translations
     */
    @PostMapping(value = "/translation", produces = "application/json")
    public ResponseEntity<MultitranTranslationResponse> translate(@RequestBody MultitranTranslationRequest body) {
        MultitranTranslationResponse response = createMultitranTranslationResponse(body);
        try {
            List<MultitranTranslation> translations = client.getTranslations(body.getSourceLanguage(), body.getTargetLanguage(), body.getPhrase());
            response.setTranslations(translations);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    private MultitranTranslationResponse createMultitranTranslationResponse(MultitranTranslationRequest payload) {
        String url = client.getTranslationUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
        MultitranTranslationResponse response = new MultitranTranslationResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(url);
        return response;
    }
}
