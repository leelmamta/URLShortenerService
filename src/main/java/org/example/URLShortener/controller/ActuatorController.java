package org.example.URLShortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @GetMapping("/status")
    public String getCustomStatus() {
        return "Application is running fine!";
    }

}
