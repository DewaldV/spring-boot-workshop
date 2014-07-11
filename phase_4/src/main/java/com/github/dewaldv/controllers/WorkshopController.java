package com.github.dewaldv.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkshopController {

    @RequestMapping(value = "/", produces = "application/json")
    public String get() {
        return "Hello World!";
    }
}
