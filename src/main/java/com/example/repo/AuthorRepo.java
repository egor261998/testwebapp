package com.example.repo;

import com.example.domain.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Integer> {
    Page<Author> findByNameOrderByNameAsc(String text, Pageable pageable);
    Page<Author> findAllByOrderByNameAsc(Pageable pageable);

    Author findAuthorByName(String text);
    Author findById(int id);
}
