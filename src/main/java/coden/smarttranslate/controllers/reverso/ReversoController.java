package coden.smarttranslate.controllers.reverso;

import coden.reverso.ReversoClient;
import coden.reverso.website.ReversoUrls;
import coden.smarttranslate.controllers.reverso.data.ReversoContextResponse;
import coden.smarttranslate.controllers.reverso.data.ReversoRequest;
import coden.smarttranslate.controllers.reverso.data.ReversoTranslationResponse;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reverso")
public class ReversoController {


    private final ReversoClient client;

    public ReversoController(ReversoClient client) {
        this.client = client;
    }

    /**
     * Returns {@link ReversoContextResponse} containing list of contexts, that were parsed
     * from context.reverso.net.
     * Following Status Codes could be returned by calling this request:
     * 1) 100 - OK, contains list of contexts (may be empty)
     * 2) 400 - Bad Request, if {@link ReversoRequest} does not contain necessary information
     * 3) 503 - Service Unavailable, if context.reverso.net cannot be accessed for some reason.
     *
     * @param request
     *         the {@link ReversoRequest} that holds request to fetching the contexts
     * @return the {@link ReversoContextResponse} containing contexts
     */
    @PostMapping(value = "/context", produces = "application/json")
    public ResponseEntity<ReversoContextResponse> context(@RequestBody ReversoRequest request) {
        ReversoContextResponse response = new ReversoContextResponse(request);
        response.setUrl(ReversoUrls.getContextUrl(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase()));
        try {
            response.setContexts(client.getContexts(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase())
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(response);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                response.setContexts(Collections.emptyList());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    /**
     * Returns {@link ReversoTranslationResponse} containing list of translations, that were parsed
     * from reverso API.
     * Following Status Codes could be returned by calling this request:
     * 1) 100 - OK, contains list of translations (may be empty)
     * 2) 400 - Bad Request, if {@link ReversoRequest} does not contain necessary information
     * 3) 503 - Service Unavailable, if reverso API cannot be accessed for some reason.
     *
     * @param request
     *         the {@link ReversoRequest} that holds request to fetching the contexts
     * @return the {@link ReversoContextResponse} containing contexts
     */
    @PostMapping(value = "/translation", produces = "application/json")
    public ResponseEntity<ReversoTranslationResponse> translation(@RequestBody ReversoRequest request) {
        ReversoTranslationResponse response = new ReversoTranslationResponse(request);
        response.setUrl(ReversoUrls.getTranslationUrl(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase()));
        try {
            response.setContexts(client.getTranslations(request.getSourceLanguage(), request.getTargetLanguage(), request.getPhrase())
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(response);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                response.setContexts(Collections.emptyList());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }
}
