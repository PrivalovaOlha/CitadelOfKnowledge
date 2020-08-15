package lesson_x.library.io.commandline.bookHistory;

import lesson_x.library.io.commandline.CommandProcessor;
import lesson_x.library.services.BookHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookHistoryTakeCommandProcessor implements BookHistoryRelatedCommandProcessor {
    private final BookHistoryService historyService;

    @Autowired
    public BookHistoryTakeCommandProcessor(BookHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("take");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of a book");
        Long bookId = in.nextLong();
        System.out.println("Input id of a customer");
        Long customerId = in.nextLong();
        historyService.takeBook(bookId, customerId);
        System.out.println("Book was gave out");
    }
}
