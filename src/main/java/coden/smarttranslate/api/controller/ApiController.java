package coden.smarttranslate.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api/smarttrans")
    public String test(){
        return "It worked.";
    }
}
