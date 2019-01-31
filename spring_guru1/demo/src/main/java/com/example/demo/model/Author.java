package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by jt on 5/16/17.
 */
@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy=AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    //Add mappedBy to prevent 2 join tables being created - AUTHORS_BOOKS and BOOKS_AUTHORS
    @ManyToMany(mappedBy="authors", cascade = CascadeType.ALL)
    private Set<Book> books  = new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}