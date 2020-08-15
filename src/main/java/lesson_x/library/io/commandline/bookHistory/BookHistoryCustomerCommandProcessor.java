package lesson_x.library.io.commandline.bookHistory;

import lesson_x.library.StringUtils;
import lesson_x.library.domain.BookHistory;
import lesson_x.library.io.commandline.CommandProcessor;
import lesson_x.library.services.BookHistoryService;
import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BookHistoryCustomerCommandProcessor implements BookHistoryRelatedCommandProcessor {
    private final BookHistoryService historyService;
    private final BookService bookService;
    private static final String SPACES = StringUtils.repeat(30, StringUtils.SPACE);

    @Autowired
    public BookHistoryCustomerCommandProcessor(BookHistoryService historyService, BookService bookService) {
        this.historyService = historyService;
        this.bookService = bookService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("customer");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of customer");
        long customerId = in.nextLong();
        List<BookHistory> histories = historyService.getByCustId(customerId);
        System.out.println(
                "| " + ("Id of book:  " + SPACES).substring(0, 30) +
                        "| " + ("Name of book: " + SPACES).substring(0, 30) +
                        "| " + ("Date when book was taken  " + SPACES).substring(0, 30) +
                        "| " + ("Date when book was returned " + SPACES).substring(0, 30) + " |\n"
        );

        for (BookHistory history : histories) {
            System.out.println("**************");
            Long bookId = history.getId();
            String lost = " ";
            String lostBook = " ";
            if (history.getLost() == null) {
                lost = " (lost)";
            }
            if (bookService.getBook(bookId).isLost()) {
                lostBook = " (lost)";
            }
            String returnDate = history.getReturnDate() == null ? "Book was not returned" : history.getReturnDate() + "";
            System.out.println(
                    "| " + (bookId + lostBook + SPACES).substring(0, 30) +
                            "| " + (bookService.getBook(bookId).getBookName() + SPACES).substring(0, 30) +
                            "| " + (history.getTakeDate() + SPACES).substring(0, 30) +
                            "| " + (returnDate + lost + SPACES).substring(0, 30) + " |");


        }
        System.out.println("List is over");
    }
}
