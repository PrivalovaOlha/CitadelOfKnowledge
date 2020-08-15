package lesson_x.library.io.commandline.bookHistory;

import lesson_x.library.services.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookHistoryReturnCommandProcessor implements BookHistoryRelatedCommandProcessor {
    private final BookHistoryService historyService;

    @Autowired
    public BookHistoryReturnCommandProcessor(BookHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("return");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of a book");
        long bookId = in.nextLong();
        historyService.returnBook(bookId);
        System.out.println("Book was returned");
    }
}
