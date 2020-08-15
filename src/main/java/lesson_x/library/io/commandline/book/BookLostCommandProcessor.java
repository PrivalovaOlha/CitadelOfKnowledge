package lesson_x.library.io.commandline.book;

import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookLostCommandProcessor implements BookRelatedCommandProcessor {

    private final BookService bookService;

    @Autowired
    public BookLostCommandProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean canProcess(Scanner command) {
        return command.hasNext("lost");
    }

    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of book which was losted");
        Long bookId = in.nextLong();
        bookService.lostBook(bookId);
        System.out.println("Book was marked as losted");
    }
}
