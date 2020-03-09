package com.example.service;

import com.example.Exception.NotFoundException;
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

    //Получить список авторов из бд.
    public List<Author> GetAuthors(String filter)
    {
        Iterable<Author> authors;

        if(filter!=null && !filter.isEmpty())
            authors = authorRepo.findByNameOrderByNameAsc(filter);
        else
            authors = authorRepo.findAllByOrderByNameAsc();

        List<Author> authorlist =new ArrayList<>();
        authors.forEach(authorlist::add);

        return authorlist;
    }

    //Информация об авторе.
    public Author AddBookPage(String authorid)
    {
        return authorRepo.findById(Integer.parseInt(authorid));
    }

    //Добавить книгу к автору.
    public Author AddBookToAuthor(String authorid, String bookname)
    {
        //получаем книгу из бд
        Book book = bookRepo.findBookByName(bookname);
        Author author = authorRepo.findById(Integer.parseInt(authorid));
        if(book==null)
            throw new NotFoundException("Книга не существует, сначала добавте ее.");

        if(author==null)
            throw new NotFoundException("Автор не существует.");

        //теперь можно вписать автора в кингу.
        Set<Book> books = author.getBooks();
        books.add(book);
        author.setBooks(books);
        authorRepo.save(author);

        //а книге добавим автора
        Set<Author> authors = book.getAuthors();
        authors.add(author);
        book.setAuthors(authors);
        bookRepo.save(book);

        return author;
    }
}
