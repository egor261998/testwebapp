package com.example.controller;

import com.example.domain.entitydriver.AuthorDriver;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @PostMapping("author")
    public String post_author(@RequestParam String filter, Model model) {
        model.addAttribute("authors", AuthorDriver.GetAuthors(authorRepo, filter));

        return "authors";
    }

    @PostMapping("addbook")
    public String post_addbook(@RequestParam String authorid, Model model) {
        model.addAttribute("author", AuthorDriver.AddBookPage(authorRepo, authorid));

        return "authorid";
    }

    @PostMapping("addbooktoauthor")
    public String post_book_to_addauthor(@RequestParam String authorid, @RequestParam String name, Model model) {
        model.addAttribute("author", AuthorDriver.AddBookToAuthor(authorRepo, bookRepo, authorid, name));

        return "authorid";
    }
}
