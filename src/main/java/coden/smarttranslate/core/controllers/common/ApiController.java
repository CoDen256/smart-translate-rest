package coden.smarttranslate.core.controllers.common;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping(value="/ping", produces = "text/plain")
    public String ping(){
        return "It works :)";
    }
}
