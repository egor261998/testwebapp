package com.example.controller;

import com.example.domain.entity.Book;
import com.example.service.BookService;
import org.springframework.data.domain.Page;
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

    //последние 10, добавленных, книг.
    @PostMapping("last")
    public String post_last(Model model) {

        List<Book> books = service.GetLast10Books();
        model.addAttribute("books",  books);

        return "BookPages/last";
    }

    //перейти на страничку с добавлением новой книги.
    @PostMapping("newbook")
    public String post_newbook(Model model) {
        return "BookPages/newbook";
    }

    //добавить новую книгу.
    @PostMapping("addnewbook")
    public String get_addbook(@RequestParam String name,
                              Model model) {

        model.addAttribute("book", service.AddNewBook(name));

        return "BookPages/bookid";
    }
    //вывести список книг.
    @PostMapping("book")
    public String post_book(@RequestParam(name="filter" ,required = false,defaultValue = "") String filter,
                            @RequestParam(name="pagecur" ,required = false,defaultValue = "0") Integer pagecur,
                            @RequestParam(name="pagemax" ,required = false,defaultValue = "0") Integer pagemax,
                            Model model) {

        Page<Book> page = service.GetBooks(filter,pagecur,pagemax);


        model.addAttribute("books",  page.getContent());
        model.addAttribute("pagecur",page.getNumber());
        model.addAttribute("pagemax",page.getTotalPages());

        return "BookPages/books";
    }

    //информация о книге.
    @PostMapping("addauthor")
    public String post_addauthor(@RequestParam String bookid, Model model) {
        model.addAttribute("book", service.AddAuthorPage(bookid));

        return "BookPages/bookid";
    }

    //добавить автора к книге.
    @PostMapping("addauthortobook")
    public String post_addauthor_to_book(@RequestParam String bookid, @RequestParam String name, Model model) {
        model.addAttribute("book", service.AddAuthorToBook(bookid, name));

        return "BookPages/bookid";
    }
}
