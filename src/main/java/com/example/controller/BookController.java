package com.example.controller;

import com.example.domain.entity.Book;
import com.example.domain.entitydriver.BookDriver;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @PostMapping("last")
    public String post_last(Model model) {

        List<Book> books = BookDriver.GetLastBooks(bookRepo);
        model.addAttribute("books",  books);

        return "last";
    }

    @PostMapping("newbook")
    public String post_newbook(Model model) {
        return "newbook";
    }

    @PostMapping("addnewbook")
    public String get_addbook(@RequestParam String name, Model model) {
        model.addAttribute("book", BookDriver.AddNewBook(bookRepo,name));

        return "bookid";
    }

    @PostMapping("book")
    public String post_book(@RequestParam String filter, Model model) {
        model.addAttribute("books",  BookDriver.GetBooks(bookRepo,filter));

        return "books";
    }

    @PostMapping("addauthor")
    public String post_addauthor(@RequestParam String bookid, Model model) {
        model.addAttribute("book", BookDriver.AddAuthorPage(bookRepo,bookid));

        return "bookid";
    }

    @PostMapping("addauthortobook")
    public String post_addauthor_to_book(@RequestParam String bookid, @RequestParam String name, Model model) {
        model.addAttribute("book", BookDriver.AddAuthorToBook(bookRepo,authorRepo,bookid, name));

        return "bookid";
    }
}
