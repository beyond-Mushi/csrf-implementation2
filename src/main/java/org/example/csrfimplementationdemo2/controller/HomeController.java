package org.example.csrfimplementationdemo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HomeController {

    @GetMapping("/home")
    public String fdsa() {

        return "Hello Get Home!";
    }

    @PostMapping("/home")
    public String asdf() {

        return "Hello Post Home!";
    }
}
