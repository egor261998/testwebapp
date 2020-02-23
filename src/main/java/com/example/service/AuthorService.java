package com.example.service;

import com.example.domain.entity.Author;
import com.example.domain.entity.Book;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public AuthorService(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public List<Author> GetAuthors(String filter)
    {
        Iterable<Author> authors;

        if(filter!=null && !filter.isEmpty())
            authors = authorRepo.findByNameOrderByNameAsc(filter);
        else
            authors = authorRepo.findAllByOrderByNameDesc();

        List<Author> authorlist =new ArrayList<>();
        authors.forEach(authorlist::add);

        return authorlist;
    }

    public Author AddBookPage(String authorid)
    {
        return authorRepo.findById(Integer.parseInt(authorid));
    }

    public Author AddBookToAuthor(String authorid, String name)
    {
        Book book= bookRepo.findAuthorByName(name);
        Author author = authorRepo.findById(Integer.parseInt(authorid));

        if(author==null)
            return null;

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

        return author;
    }
}
