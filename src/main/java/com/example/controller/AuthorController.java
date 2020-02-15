package com.example.controller;

import com.example.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping("author")
    public String post_author(@RequestParam String filter, Model model) {
        model.addAttribute("authors", service.GetAuthors(filter));

        return "authors";
    }

    @PostMapping("addbook")
    public String post_addbook(@RequestParam String authorid, Model model) {
        model.addAttribute("author", service.AddBookPage(authorid));

        return "authorid";
    }

    @PostMapping("addbooktoauthor")
    public String post_book_to_addauthor(@RequestParam String authorid, @RequestParam String name, Model model) {
        model.addAttribute("author", service.AddBookToAuthor(authorid, name));

        return "authorid";
    }
}
