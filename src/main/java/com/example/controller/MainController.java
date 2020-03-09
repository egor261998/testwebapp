package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping
    public String get_main(Model model) {
        return "main/main";
    }

    @PostMapping("main")
    public String post_main(Model model) {
        return "main/main";
    }
}
