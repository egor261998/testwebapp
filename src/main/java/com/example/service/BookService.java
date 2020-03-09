package com.example.service;

import com.example.Exception.NotFoundException;
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

    //Получить список книг из бд.
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

    //Получить последние 10 добавленных книг.
    public List<Book> GetLast10Books()
    {
        return bookRepo.findFirst10ByOrderByIdDesc();
    }

    //Добавить новую книгу
    public Book AddNewBook(String name)
    {
        Book book = bookRepo.findBookByName(name);

        if(book!=null)
            throw new NotFoundException("Книга уже добавлена");

        book = new Book(name);
        bookRepo.save(book);

        return book;
    }

    //Информация о книге.
    public Book AddAuthorPage(String bookid)
    {
        return bookRepo.findById(Integer.parseInt(bookid));
    }

    //Добавить автора к книге.
    public Book AddAuthorToBook(String bookid, String nameauthor)
    {
        Author author = authorRepo.findAuthorByName(nameauthor);

        if(author==null)
        {
            //создаем автора
            author = new Author(nameauthor);
            authorRepo.save(author);
        }

        Book book = bookRepo.findById(Integer.parseInt(bookid));

        if(book==null)
            throw new NotFoundException("Книга не существует, сначала добавте ее.");

        //теперь можно книге добавить автора.
        Set<Author> authors = book.getAuthors();
        authors.add(author);
        book.setAuthors(authors);
        bookRepo.save(book);

        //а автору добавить книгу
        Set<Book> books = author.getBooks();
        books.add(book);
        author.setBooks(books);
        authorRepo.save(author);

        return book;
    }
}
