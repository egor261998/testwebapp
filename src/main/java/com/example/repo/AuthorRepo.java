package com.example.repo;

import com.example.domain.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Integer> {
    List<Author> findByName(String text);
    Author findAuthorByName(String text);
    Author findById(int id);
}
