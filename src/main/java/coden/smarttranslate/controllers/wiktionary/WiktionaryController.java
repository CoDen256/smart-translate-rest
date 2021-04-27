package coden.smarttranslate.controllers.wiktionary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/multitran")
public class WiktionaryController {


    public WiktionaryController() {
    }

    /**
     * Returns {@link WiktionaryDefinitionResponse} containing list of definitions, that were parsed
     * from wiktionary.org
     * Following Status Codes could be returned by calling this request:
     *  1) 100 - OK, contains list of definitions (may be empty)
     *  2) 400 - Bad Request, if {@link WiktionaryDefinitionRequest} does not contain necessary information
     *  3) 503 - Service Unavailable, if wiktionary.org cannot be accessed for some reason.
     *
     * @param payload the {@link WiktionaryDefinitionRequest} that holds request to translating
     * @return the {@link WiktionaryDefinitionResponse} containing translations
     */
    @PostMapping(value="/definition", produces = "application/json")
    public ResponseEntity<WiktionaryDefinitionResponse> translate(@RequestBody WiktionaryDefinitionRequest payload){
//        String url = crawler.getUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());


//        WiktionaryDefinitionResponse response = createMultitranTranslationResponse(payload, url);
//        try {
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
//        }
//            return ResponseEntity.ok(response);
        return null;
    }

    private WiktionaryDefinitionResponse createMultitranTranslationResponse(WiktionaryDefinitionRequest payload, String url) {
        WiktionaryDefinitionResponse response = new WiktionaryDefinitionResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(url);
        return response;
    }
}
