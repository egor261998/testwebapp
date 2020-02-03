package com.example.domain.entitydriver;

import com.example.domain.entity.Author;
import com.example.domain.entity.Book;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BookDriver {

    public static List<Book> GetBooks(BookRepo bookRepo, String filter)
    {
        Iterable<Book> books;

        if(filter!=null && !filter.isEmpty())
            books = bookRepo.findByName(filter);
        else
            books = bookRepo.findAll();

        List<Book> booklist =new ArrayList<>();
        books.forEach(booklist::add);
        Collections.sort(booklist, Book.NameComparator);

        return booklist;
    }

    public static List<Book> GetLastBooks(BookRepo bookRepo)
    {
        return bookRepo.findFirst10ByOrderByIdDesc();
    }

    public static Book AddNewBook(BookRepo bookRepo, String name)
    {
        Book book = bookRepo.findBookByName(name);

        if(book!=null)
            return book;

        book = new Book(name);
        bookRepo.save(book);

        return book;
    }

    public static Book AddAuthorPage(BookRepo bookRepo, String bookid)
    {
        return bookRepo.findById(Integer.parseInt(bookid));
    }

    public static Book AddAuthorToBook(BookRepo bookRepo, AuthorRepo authorRepo, String bookid, String name)
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
