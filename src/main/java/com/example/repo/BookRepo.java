package com.example.repo;

import com.example.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
    Page<Book> findByNameOrderByNameAsc(String text, Pageable pageable);
    Page<Book> findAllByOrderByNameAsc(Pageable pageable);
    List<Book> findFirst10ByOrderByIdDesc();

    Book findBookByName(String name);
    Book findById(int id);
    }