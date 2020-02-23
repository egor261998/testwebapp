package com.example.repo;

import com.example.domain.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
    List<Book> findByNameOrderByNameAsc(String text);
    List<Book> findAllByOrderByNameAsc();
    List<Book> findFirst10ByOrderByIdDesc();
    Book findBookByName(String name);
    Book findById(int id);
    Book findAuthorByName(String authorkid);
}
