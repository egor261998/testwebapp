package com.example.repos;

import com.example.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookRepo extends CrudRepository<Book, Integer> {
    List<Book> findByName(String text);
    Book findById(int id);

    Book findAuthorByName(String authorkid);
}
