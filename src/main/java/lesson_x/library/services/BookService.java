package lesson_x.library.services;

import lesson_x.library.domain.Book;
import lesson_x.library.persistence.services.IBookPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//        CPU  -   Central processor unit
//        RAM  -   random access memory (ОЗУ)
//        SSD/HDD - Хранится информация

@Service
public class BookService {

    private final IBookPersistenceService repository;

    @Autowired
    public BookService(IBookPersistenceService repository) {
        this.repository = repository;
    }


    public Book addBook(String name, String author) {
        Book book = new Book();
        book.setBookName(name);
        book.setAuthor(author);
        return update(book);
    }

    public Book deleteBook(Long bookId) {
        Book book = repository.findById(bookId).orElse(null);
        if (book != null) {
            book.setDel(true);
            book.setLost(false);
            return update(book);
        }
        return null;
    }

    public void lostBook(Long bookId) {
        Book book = repository.findById(bookId).orElse(null);
        if (book != null) {
            book.setDel(true);
            book.setLost(true);
            update(book);
        }
    }

    public Book getBook(Long bookId) {
        return repository.findById(bookId).orElse(null);
    }

    public Book update(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void updateAuthor(Long bookId, String newAuthorName) {
        Book book = repository.findById(bookId).orElse(null);
        if (book != null) {
            book.setAuthor(newAuthorName);
            update(book);
        }
    }

    public void updateBookName(Long bookId, String newBookName) {
        Book book = repository.findById(bookId).orElse(null);
        if (book != null) {
            book.setBookName(newBookName);
            update(book);
        }
    }


}
