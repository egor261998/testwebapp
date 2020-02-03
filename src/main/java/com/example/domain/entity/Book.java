package com.example.domain.entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<Author>();

    public Book() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Book(String name) {
        this.name = name;
    }

    public static Comparator<Book> NameComparator
            = new Comparator<Book>() {

        public int compare(Book book1, Book book2) {

            String Name1 = book1.getName().toUpperCase();
            String Name2 = book2.getName().toUpperCase();

            //ascending order
            return Name1.compareTo(Name2);

            //descending order
            // return Name2.compareTo(Name1);
        }
    };


}
