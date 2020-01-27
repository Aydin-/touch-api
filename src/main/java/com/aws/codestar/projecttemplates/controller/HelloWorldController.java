package com.aws.codestar.projecttemplates.controller;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class HelloWorldController {

    private static boolean isTouched = false;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getIstouched(String test) {
        String response = "";
        if (isTouched) response = "true";
        else response = "false";
        isTouched = false;
        return ResponseEntity.ok(createResponse(response));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity setIsTouched(String test) {
        isTouched = true;
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isTouched = false;
        return ResponseEntity.ok(createResponse(""));
    }

    private String createResponse(String responseStr) {
        return new JSONObject().put("touched", responseStr).toString();
    }

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
            return Flux.interval(Duration.ofSeconds(1))
                    .map(sequence -> ""+isTouched );
    }
}