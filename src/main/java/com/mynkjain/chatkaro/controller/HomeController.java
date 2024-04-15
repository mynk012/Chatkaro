package com.mynkjain.chatkaro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandler() {
        return "welcome to instagram backend api";
    }

    @GetMapping("/home")
    public String homeControllerHandler2() {
        return "welcome to instagram backend api";
    }
}
