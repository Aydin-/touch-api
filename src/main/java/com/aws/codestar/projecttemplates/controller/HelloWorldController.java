package com.aws.codestar.projecttemplates.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    private static boolean isTouched = false;
    private static String temp1 = "";
    private static String temp2 = "";

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getIstouched(String test) {
        return ResponseEntity.ok(createResponse("" + isTouched));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity setIsTouched(HttpEntity<String> httpEntity) {
        isTouched = true;
        return ResponseEntity.ok(createResponse(""));
    }

    @CrossOrigin
    @RequestMapping(path = "/temp1", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity setTemp1(@RequestParam String temp1new) {
        temp1 = temp1new;
        return ResponseEntity.ok(createResponse(""));
    }

    @CrossOrigin
    @RequestMapping(path = "/temp2", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity setTemp2(@RequestParam String temp2new) {
        temp2 = temp2new;
        return ResponseEntity.ok(createResponse(""));
    }

    private String createResponse(String responseStr) {
        return new JSONObject().put("touched", responseStr).toString();
    }

    @CrossOrigin
    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "" + isTouched)
                .doOnEach(a -> isTouched = false);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping(path = "/temp1-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> temp1Flux() {
//        return Flux.interval(Duration.ofSeconds(3))
//                .map(sequence -> temp1);
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping(path = "/temp2-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> temp2Flux() {
//        return Flux.interval(Duration.ofSeconds(3))
//                .map(sequence -> temp2);
//    }
}