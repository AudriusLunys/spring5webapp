package com.springframework.spring5webapp.bootstrap;

import com.springframework.spring5webapp.domain.Author;
import com.springframework.spring5webapp.domain.Book;
import com.springframework.spring5webapp.domain.Publisher;
import com.springframework.spring5webapp.repositories.AuthorRepository;
import com.springframework.spring5webapp.repositories.BookRepository;
import com.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher= new Publisher("SomePublisher","Savanoriu 5","Vilnius");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book book = new Book("some book ","123123");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod","Rodman");
        Book book1 = new Book("another book","321256");

        rod.getBooks().add(book1);
        book1.getAuthors().add(rod);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        authorRepository.save(rod);
        bookRepository.save(book1);
        publisherRepository.save(publisher);


        System.out.println("Started in bootstrap");
        System.out.println("number of books " + bookRepository.count());
        System.out.println("number of publishers " + publisherRepository.count());


    }
}
