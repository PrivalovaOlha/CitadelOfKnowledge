package lesson_x.library.io.commandline.bookHistory;

import lesson_x.library.io.commandline.CommandProcessor;
import lesson_x.library.services.BookHistoryService;
import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookHistoryLostCommandProcessor implements BookHistoryRelatedCommandProcessor {
    private final BookHistoryService historyService;
    private final BookService bookService;

    @Autowired
    public BookHistoryLostCommandProcessor(BookHistoryService historyService, BookService bookService) {
        this.historyService = historyService;
        this.bookService = bookService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("lost");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of book which was losted");
        Long bookId = in.nextLong();
        historyService.lostBook(bookId);
        bookService.lostBook(bookId);
        System.out.println("Book was marked as losted");
    }
}
