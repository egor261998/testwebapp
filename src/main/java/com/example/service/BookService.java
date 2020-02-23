package com.example.service;

import com.example.domain.entity.Author;
import com.example.domain.entity.Book;
import com.example.repo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public BookService(final AuthorRepo authorRepo, final BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }
    public List<Book> GetBooks(String filter)
    {
        Iterable<Book> books;

        if(filter!=null && !filter.isEmpty())
            books = bookRepo.findByNameOrderByNameAsc(filter);
        else
            books = bookRepo.findAllByOrderByNameAsc();

        List<Book> booklist =new ArrayList<>();
        books.forEach(booklist::add);

        return booklist;
    }

    public List<Book> GetLastBooks()
    {
        return bookRepo.findFirst10ByOrderByIdDesc();
    }

    public Book AddNewBook(String name)
    {
        Book book = bookRepo.findBookByName(name);

        if(book!=null)
            return book;

        book = new Book(name);
        bookRepo.save(book);

        return book;
    }

    public Book AddAuthorPage(String bookid)
    {
        return bookRepo.findById(Integer.parseInt(bookid));
    }

    public Book AddAuthorToBook(String bookid, String name)
    {
        Author author = authorRepo.findAuthorByName(name);
        Book book = bookRepo.findById(Integer.parseInt(bookid));

        if(book==null)
            return null;

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

        return book;
    }
}
