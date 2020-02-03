package com.example.repo;

import com.example.domain.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
    List<Author> findByName(String text);
    Author findAuthorByName(String text);
    Author findById(int id);

}
