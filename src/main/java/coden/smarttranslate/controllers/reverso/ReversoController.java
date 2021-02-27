package coden.smarttranslate.controllers.reverso;

import coden.smarttranslate.controllers.reverso.context.ReversoContextProvider;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslation;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslationRequest;
import coden.smarttranslate.controllers.reverso.context.ReversoContextTranslationResponse;
import coden.smarttranslate.controllers.reverso.translation.ReversoTranslation;
import coden.smarttranslate.controllers.reverso.translation.ReversoTranslationProvider;
import coden.smarttranslate.controllers.reverso.translation.ReversoTranslationRequest;
import coden.smarttranslate.controllers.reverso.translation.ReversoTranslationResponse;
import coden.smarttranslate.controllers.reverso.website.ReversoContextUrlProvider;
import coden.smarttranslate.controllers.reverso.website.ReversoTranslationUrlProvider;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reverso")
public class ReversoController {

    private final ReversoContextProvider contextProvider;
    private final ReversoContextUrlProvider urlProvider;
    private final ReversoTranslationProvider translationProvider;
    private final ReversoTranslationUrlProvider translationUrlProvider;

    public ReversoController(ReversoContextProvider contextProvider, ReversoTranslationProvider translationProvider,
                             ReversoContextUrlProvider ctxUrlProvider, ReversoTranslationUrlProvider translationUrlProvider) {
        this.contextProvider = contextProvider;
        this.urlProvider = ctxUrlProvider;
        this.translationProvider = translationProvider;
        this.translationUrlProvider = translationUrlProvider;
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
        ReversoContextTranslationResponse response = createReversoContextResponse(payload);
        try {
            List<ReversoContextTranslation> reversoContextTranslations = contextProvider.getContexts(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
            response.setContextTranslations(reversoContextTranslations);
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

    private ReversoContextTranslationResponse createReversoContextResponse(ReversoContextTranslationRequest payload) {
        String contextUrl = urlProvider.getContextUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
        ReversoContextTranslationResponse response = new ReversoContextTranslationResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(contextUrl);
        return response;
    }

    @PostMapping(value="/translation", produces = "application/json")
    public ResponseEntity<ReversoTranslationResponse> translation(@RequestBody ReversoTranslationRequest payload){
        ReversoTranslationResponse response = createReversoTranslationResponse(payload);
        try {
            List<ReversoTranslation> translations = translationProvider.getTranslations(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
            response.setTranslations(translations);
            return ResponseEntity.ok(response);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404){
                response.setTranslations(Collections.emptyList());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    private ReversoTranslationResponse createReversoTranslationResponse(ReversoTranslationRequest payload) {
        String contextUrl = urlProvider.getContextUrl(payload.getSourceLanguage(), payload.getTargetLanguage(), payload.getPhrase());
        ReversoTranslationResponse response = new ReversoTranslationResponse(payload.getPhrase());
        response.setSourceLanguage(payload.getSourceLanguage());
        response.setTargetLanguage(payload.getTargetLanguage());
        response.setUrl(contextUrl);
        return response;
    }
}
