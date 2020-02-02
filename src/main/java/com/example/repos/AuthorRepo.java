package com.example.repos;

import com.example.domain.Author;

import com.example.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
    List<Author> findByName(String text);
    Author findAuthorByName(String text);
    Author findById(int id);

}
