package coden.smarttranslate.core.controllers.reverso;

import coden.smarttranslate.core.controllers.reverso.data.ContextTranslation;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reverso")
public class ReversoController {

    private final ReversoCrawler crawler;

    public ReversoController() {
        crawler = new ReversoCrawler();
    }

    /**
     * Returns {@link ReversoContextTranslationResponse} containing list of contexts, that were parsed
     * from context.reverso.net.
     * Following Status Codes could be returned by calling this request:
     *  1) 100 - OK, contains list of contexts (may be empty)
     *  2) 400 - Bad Request, if {@link ReversoContextTranslationRequest} does not contain necessary information
     *  3) 503 - Service Unavailable, if context.reverso.net cannot be accessed for some reason.
     *
     * @param payload the {@link ReversoContextTranslationRequest} that holds request to fetching the contexts
     * @return the {@link ReversoContextTranslationResponse} containing contexts
     */
    @PostMapping(value="/context", produces = "application/json")
    public ResponseEntity<ReversoContextTranslationResponse> context(@RequestBody ReversoContextTranslationRequest payload){
        String url = crawler.getUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());

        ReversoContextTranslationResponse response = createReversoContextResponse(payload, url);
        try {
            List<ContextTranslation> contextTranslations = crawler.parseContextTranslation(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
            response.setContextTranslations(contextTranslations);
            return ResponseEntity.ok(response);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404){
                response.setContextTranslations(Collections.emptyList());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    private ReversoContextTranslationResponse createReversoContextResponse(ReversoContextTranslationRequest payload, String url) {
        ReversoContextTranslationResponse response = new ReversoContextTranslationResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(url);
        return response;
    }
}
