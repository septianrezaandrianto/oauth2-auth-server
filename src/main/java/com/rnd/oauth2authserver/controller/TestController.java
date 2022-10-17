package com.rnd.oauth2authserver.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String sayHello(Model model, @RequestParam(value = "name",
            defaultValue = "Septian Reza", required = false) String name) {
        model.addAttribute("name", name);
        return "Hello " + name;
    }
}
