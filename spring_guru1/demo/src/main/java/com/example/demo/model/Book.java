package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by jt on 5/16/17.
 */

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy=AUTO)
    private Long id;
    private String title;
    private String isbn;

    @OneToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    //If you omit the join table you get <owningtable>_fieldname
    //Eg in this case, with @Table(name="book") we would end up with book_authors
    @JoinTable(name="author_book"
            , joinColumns = @JoinColumn(name = "book_id")
            //inverseJoinColumn(s) specifies the fk column(s) of the join table which reference the primary table
            //of the entity that does not own the association. (I.e. the name of the column back to the non-owning table
            // - here, the authors table
            , inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<Author> authors = new HashSet<>();

    public Book() {
    }

    public Book(String title, String isbn, Publisher publisher) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public Book(String title, String isbn, Publisher publisher, Set<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}