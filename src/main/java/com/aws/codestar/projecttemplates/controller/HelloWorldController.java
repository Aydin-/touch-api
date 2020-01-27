package com.aws.codestar.projecttemplates.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    private static boolean isTouched = false;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getIstouched() {
        String response = "";
        if (isTouched) response = "true";
        else response = "false";
        isTouched = false;
        return ResponseEntity.ok(createResponse(response));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity setIsTouched() {
        isTouched = true;
        return ResponseEntity.ok(createResponse(""));
    }

    private String createResponse(String responseStr) {
        return new JSONObject().put("touched", responseStr).toString();
    }
}