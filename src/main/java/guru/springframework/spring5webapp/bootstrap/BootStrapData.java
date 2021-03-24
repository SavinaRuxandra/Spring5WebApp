package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
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

        System.out.println("Started in Bootstrap");

        //Publishers
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        //Books
        Book ddd = new Book("Domain Driven Design", "123123");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        //Authors
        Author eric = new Author("Eric", "Evans");
        Author rod = new Author("Rod", "Johnson");

        authorRepository.save(eric);
        authorRepository.save(rod);

        //Common
        publisher.getBooks().add(ddd);
        publisher.getBooks().add(noEJB);
        publisherRepository.save(publisher);

        noEJB.getAuthors().add(rod);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        noEJB.setPublisher(publisher);
        publisherRepository.save(publisher);

        eric.getBooks().add(ddd);
        rod.getBooks().add(noEJB);

        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}