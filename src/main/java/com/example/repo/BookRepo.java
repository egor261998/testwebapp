package com.example.repo;

import com.example.domain.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Integer> {
    List<Book> findByName(String text);
    Book findBookByName(String name);
    Book findById(int id);

    Book findAuthorByName(String authorkid);
}
