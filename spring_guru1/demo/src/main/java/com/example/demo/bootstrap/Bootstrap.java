package com.example.demo.bootstrap;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private AuthorRepository authorRepository;
    //private BookRepository bookRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        //this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        logger.info("initData!");
        Author author1 = new Author("Joe", "Bloggs");
        Book b1 = new Book("How to grow eggs", "1234", new Publisher("Puffin"));
        author1.getBooks().add(b1);
        b1.getAuthors().add(author1);

        authorRepository.save(author1);

        Author author2 = new Author("Fred", "Perry");
        Publisher publisher = new Publisher("Penguin");
        Book b2 = new Book("How to grow spaghetti", "4545", publisher);
        Book b3 = new Book("How to brew beer", "2233", publisher);
        author2.getBooks().add(b2);
        author2.getBooks().add(b3);
        b2.getAuthors().add(author2);
        b3.getAuthors().add(author2);
        authorRepository.save(author2);
    }
}
