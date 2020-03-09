package com.example.service;

import com.example.Exception.NotFoundException;
import com.example.domain.entity.Author;
import com.example.domain.entity.Book;
import com.example.repo.AuthorRepo;
import com.example.repo.BookRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Author> GetAuthors(String filter,int cur, int max)
    {
        Page<Author> authors;
        Pageable pageable;

        if(max==0)
        {
            pageable = PageRequest.of(0,10);
        }
        else
        {
            pageable = PageRequest.of((cur + 1 ) % max,10);
        }

        if(filter!=null && !filter.isEmpty())
            authors = authorRepo.findByNameOrderByNameAsc(filter,pageable);
        else
            authors = authorRepo.findAllByOrderByNameAsc(pageable);

        return authors;
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
