package com.example.controller;

import com.example.domain.entity.Author;
import com.example.service.AuthorService;
import org.springframework.data.domain.Page;
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

    //вывести список авторов
    @PostMapping("author")
    public String post_author(@RequestParam(name="filter" ,required = false,defaultValue = "") String filter,
                              @RequestParam(name="pagecur" ,required = false,defaultValue = "0") Integer pagecur,
                              @RequestParam(name="pagemax" ,required = false,defaultValue = "0") Integer pagemax,
                              Model model) {


        Page<Author> page = service.GetAuthors(filter,pagecur,pagemax);

        model.addAttribute("authors",page.getContent() );
        model.addAttribute("pagecur",page.getNumber());
        model.addAttribute("pagemax",page.getTotalPages());

        return "AuthorPages/authors";
    }

    //информация об авторе
    @PostMapping("addbook")
    public String post_addbook(@RequestParam String authorid,
                               Model model) {
        model.addAttribute("author", service.AddBookPage(authorid));

        return "AuthorPages/authorid";
    }

    //добавить книгу к автору.
    @PostMapping("addbooktoauthor")
    public String post_book_to_addauthor(@RequestParam String authorid, @RequestParam String name, Model model) {
        model.addAttribute("author", service.AddBookToAuthor(authorid, name));

        return "AuthorPages/authorid";
    }
}
