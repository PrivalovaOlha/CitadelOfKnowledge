package lesson_x.library.io.commandline.bookHistory;

import lesson_x.library.StringUtils;
import lesson_x.library.domain.BookHistory;
import lesson_x.library.services.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BookHistoryByBookCommandProcessor implements BookHistoryRelatedCommandProcessor {
    private final BookHistoryService historyService;

    private static final String SPACES = StringUtils.repeat(30, StringUtils.SPACE);

    @Autowired
    public BookHistoryByBookCommandProcessor(BookHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("book");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of book");
        long bookId = in.nextLong();
        List<BookHistory> histories = historyService.getByBookId(bookId);
        System.out.println(
                "| " + ("Id of customer:  " + SPACES).substring(0, 30) +
                        "| " + ("Date when book was taken  " + SPACES).substring(0, 30) +
                        "| " + ("Date when book was returned " + SPACES).substring(0, 30) + " |\n"
        );

        for (BookHistory history : histories) {
            Long bookId1 = history.getId();
            Long custId = history.getCustomerId();
            String lost = " ";
            String lostBook = " ";
            if (history.getLost() != null) {
                lost = " (lost)";
            }
            String returnDate = history.getReturnDate() == null ? "Book was not returned" : history.getReturnDate() + "";
            System.out.println(
                    "| " + (custId + SPACES).substring(0, 30) +
                            "| " + (history.getTakeDate() + SPACES).substring(0, 30) +
                            "| " + (returnDate + lost + SPACES).substring(0, 30) + " |");
        }
        System.out.println("List is over");
    }
}
