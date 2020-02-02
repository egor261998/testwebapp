package com.example;

import com.example.domain.Author;
import com.example.domain.Book;
import com.example.repos.AuthorRepo;
import com.example.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    @GetMapping
    public String get_main(Model model) {
        return "main";
    }

    @PostMapping("main")
    public String post_main(Model model) {
        return "main";
    }

    @GetMapping("newbook")
    public String get_newbook(Model model) {
        return "newbook";
    }

    @PostMapping ("newbook")
    public String post_newbook(Model model) {
        return "newbook";
    }

    @GetMapping("author")
    public String get_author(Model model) {
        Iterable<Author> authors;

        authors = authorRepo.findAll();

        model.addAttribute("authors", authors);

        return "authors";
    }

    @PostMapping("author")
    public String post_author(@RequestParam String filter, Model model)    {
        Iterable<Author> authors;
        if(filter!=null && !filter.isEmpty())
            authors = authorRepo.findByName(filter);
        else
            authors = authorRepo.findAll();

        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("book")
    public String get_book(Model model) {
        Iterable<Book> books;

        books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("book")
    public String post_book(@RequestParam String filter, Model model) {

        Iterable<Book> books;
        if(filter!=null && !filter.isEmpty())
            books = bookRepo.findByName(filter);
        else
            books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("last")
    public String post_last(Model model) {

        Iterable<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "last";
    }

    @GetMapping("last")
    public String get_last(Model model) {

        Iterable<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "last";
    }

    @PostMapping("addnewbook")
    public String get_addbook(@RequestParam String name, Model model) {
        Book book = new Book(name);
        bookRepo.save(book);

        model.addAttribute("book", book);

        return "bookid";
    }

    @PostMapping("addbook")
    public String post_addbook(@RequestParam String authorid, Model model) {

        Author author = authorRepo.findById(Integer.parseInt(authorid));
        model.addAttribute("author", author);

        return "authorid";
    }

    @PostMapping("addauthor")
    public String post_addauthor(@RequestParam String bookid, Model model) {

        Book book = bookRepo.findById(Integer.parseInt(bookid));
        model.addAttribute("book", book);

        return "bookid";
    }

    @PostMapping("addauthortobook")
    public String post_addauthor_to_book(@RequestParam String bookid, @RequestParam String name, Model model) {

        Author author = authorRepo.findAuthorByName(name);
        Book book = bookRepo.findById(Integer.parseInt(bookid));

        if(book==null)
            return "bookid";

        Set<Author> authors = book.getAuthors();
        if(author!=null && !authors.contains(author))
            authors.add(author);
        else
        {
            author = new Author(name);
            authorRepo.save(author);
            authors.add(author);
        }

        book.setAuthors(authors);
        bookRepo.save(book);
        Set<Book> books = author.getBooks();
        books.add(book);
        author.setBooks(books);
        authorRepo.save(author);

        model.addAttribute("book", book);
        return "bookid";
    }

    @PostMapping("addbooktoauthor")
    public String post_book_to_addauthor(@RequestParam String authorid, @RequestParam String name, Model model) {

        Book book= bookRepo.findAuthorByName(name);
        Author author = authorRepo.findById(Integer.parseInt(authorid));

        if(author==null)
            return "authorid";

        Set<Book> books = author.getBooks();
        if(book!=null && !books.contains(book))
            books.add(book);
        else
        {
            book = new Book(name);
            bookRepo.save(book);
            books.add(book);
        }

        author.setBooks(books);
        authorRepo.save(author);
        Set<Author> authors = book.getAuthors();
        authors.add(author);
        book.setAuthors(authors);
        bookRepo.save(book);

        model.addAttribute("author", author);
        return "authorid";
    }
}
