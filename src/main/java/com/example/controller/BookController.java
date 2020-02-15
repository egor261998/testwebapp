package com.example.controller;

import com.example.domain.entity.Book;
import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("last")
    public String post_last(Model model) {

        List<Book> books = service.GetLastBooks();
        model.addAttribute("books",  books);

        return "last";
    }

    @PostMapping("newbook")
    public String post_newbook(Model model) {
        return "newbook";
    }

    @PostMapping("addnewbook")
    public String get_addbook(@RequestParam String name, Model model) {
        model.addAttribute("book", service.AddNewBook(name));

        return "bookid";
    }

    @PostMapping("book")
    public String post_book(@RequestParam String filter, Model model) {
        model.addAttribute("books",  service.GetBooks(filter));

        return "books";
    }

    @PostMapping("addauthor")
    public String post_addauthor(@RequestParam String bookid, Model model) {
        model.addAttribute("book", service.AddAuthorPage(bookid));

        return "bookid";
    }

    @PostMapping("addauthortobook")
    public String post_addauthor_to_book(@RequestParam String bookid, @RequestParam String name, Model model) {
        model.addAttribute("book", service.AddAuthorToBook(bookid, name));

        return "bookid";
    }
}
