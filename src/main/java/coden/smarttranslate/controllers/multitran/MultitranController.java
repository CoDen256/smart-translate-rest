package coden.smarttranslate.controllers.multitran;

import static coden.multitran.website.MultitranUrls.getTranslationUrl;

import coden.multitran.translation.MultitranTranslationClient;
import coden.smarttranslate.controllers.multitran.data.MultitranRequest;
import coden.smarttranslate.controllers.multitran.data.MultitranTranslationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/multitran")
public class MultitranController {

    private final MultitranTranslationClient client;

    public MultitranController(MultitranTranslationClient client) {
        this.client = client;
    }

    /**
     * Returns {@link MultitranTranslationResponse} containing list of translations, that were parsed
     * from www.multitran.com.
     * Following Status Codes could be returned by calling this request:
     * 1) 100 - OK, contains list of translations (may be empty)
     * 2) 400 - Bad Request, if {@link MultitranRequest} does not contain necessary information
     * 3) 503 - Service Unavailable, if www.multitran.com cannot be accessed for some reason.
     *
     * @param request
     *         the {@link MultitranRequest} that holds request to translating
     * @return the {@link MultitranTranslationResponse} containing translations
     */
    @PostMapping(value = "/translation", produces = "application/json")
    public ResponseEntity<MultitranTranslationResponse> translate(@RequestBody MultitranRequest request) {
        MultitranTranslationResponse response = new MultitranTranslationResponse(request);
        response.setUrl(getTranslationUrl(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase()));
        try {
            response.setTranslations(client.getTranslations(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase())
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }
}
