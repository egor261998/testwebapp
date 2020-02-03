package com.example.domain.entitydriver;

import com.example.domain.entity.Author;
import com.example.domain.entity.Book;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AuthorDriver {
    public static List<Author> GetAuthors(AuthorRepo authorRepo, String filter)
    {
        Iterable<Author> authors;

        if(filter!=null && !filter.isEmpty())
            authors = authorRepo.findByName(filter);
        else
            authors = authorRepo.findAll();

        List<Author> authorlist =new ArrayList<>();
        authors.forEach(authorlist::add);
        Collections.sort(authorlist, Author.NameComparator);

        return authorlist;
    }

    public static Author AddBookPage(AuthorRepo authorRepo, String authorid)
    {
        return authorRepo.findById(Integer.parseInt(authorid));
    }

    public static Author AddBookToAuthor(AuthorRepo authorRepo,BookRepo bookRepo, String authorid, String name)
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
