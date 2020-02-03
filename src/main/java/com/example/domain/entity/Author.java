package com.example.domain.entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name="author_book",
            joinColumns={@JoinColumn(name="author_id")},
            inverseJoinColumns={@JoinColumn(name="book_id")})
    private Set<Book> books = new HashSet<Book>();

    public Author() { }

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public static Comparator<Author> NameComparator
            = new Comparator<Author>() {

        public int compare(Author author1, Author author2) {

            String Name1 = author1.getName().toUpperCase();
            String Name2 = author2.getName().toUpperCase();

            //ascending order
            return Name1.compareTo(Name2);

            //descending order
            // return Name2.compareTo(Name1);
        }
    };
}
