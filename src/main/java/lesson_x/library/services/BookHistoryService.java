package lesson_x.library.services;

import lesson_x.library.domain.BookHistory;
import lesson_x.library.persistence.services.IBookHistoryPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookHistoryService {


    private final IBookHistoryPersistenceService repository;

    @Autowired
    public BookHistoryService(IBookHistoryPersistenceService repository) {
        this.repository = repository;
    }
    // private final IBookHistoryPersistenceService repository = PersistenceServiceProvider.getService(IBookHistoryPersistenceService.class);

    // public static synchronized BookHistoryService getInstance() {
    //     if (instance == null) {
    //         instance = new BookHistoryService();
    //     }
    //     return instance;
    // }


    public void lostBook(Long bookHId) {
        for (BookHistory bookHistory : getByBookId(bookHId)) {
            if (bookHistory.getReturnDate() == null) {
                //   bookHistory = repository.findById(bookHId);
                bookHistory.setLost(LocalDate.now());
                update(bookHistory);
                break;
            }
        }

    }

    public List<BookHistory> getByCustId(Long id) {
        List<BookHistory> customersStories = new ArrayList<>();
        for (BookHistory bookHistory : getBookHistories()) {
            if (bookHistory.getCustomerId() == id) {
                customersStories.add(bookHistory);
            }
        }
        return customersStories;
    }

    public List<BookHistory> getByBookId(Long id) {
        List<BookHistory> bookStories = new ArrayList<>();
        for (BookHistory bookHistory : getBookHistories()) {
            if (bookHistory.getBookId() == id) {
                bookStories.add(bookHistory);
            }
        }
        return bookStories;
    }

    public void takeBook(Long bookId, Long customerId) {
        BookHistory bookHistory = new BookHistory();
        bookHistory.setCustomerId(customerId);
        bookHistory.setBookId(bookId);
        bookHistory.setTakeDate(LocalDate.now());
        update(bookHistory);
    }

    public List<BookHistory> getBookHistories() {
        return repository.findAll();
    }

   /* public Collection<BookHistory> getCustomerBookHistory(Long customerId) {
        return bookHistoryM.values().stream()
                .filter(s -> s.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public void takenBooks(Map<Long, Book> allBook, Map<Long, Customer> delCustomer, Long customerId) {

        List<Long> customerUnreturnedBookId = bookHistoryM.values().stream()
                .filter(s -> s.getCustomerId().equals(customerId) && s.getReturnDate() == null)
                .map(s -> s.getBookId())
                .collect(Collectors.toList());
        for (Long lg : customerUnreturnedBookId) {
            if (allBook.get(lg).isDel() || allBook.get(lg).isLost()) {
                System.out.println(allBook.get(lg).getBookName() + "!Book was losted or deleted");
            }
            if (delCustomer.containsKey(lg)) {
                System.out.println("The customer was deleted");
            } else {
                System.out.println(allBook.get(lg).getBookName());
            }
        }
    }*/

    public void returnBook(Long bookId) {
        for (BookHistory bookHistory : getByBookId(bookId)) {
            if (bookHistory.getReturnDate() == null) {
                bookHistory.setReturnDate(LocalDate.now());
                update(bookHistory);
            }
        }
    }


    public void update(BookHistory bookHistory) {
        repository.save(bookHistory);
    }
}
