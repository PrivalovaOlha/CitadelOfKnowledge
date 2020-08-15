package lesson_x.library.io.commandline.book;

import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookDeleteCommandProcessor implements BookRelatedCommandProcessor {

    private final BookService bookService;

    @Autowired
    public BookDeleteCommandProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean canProcess(Scanner command) {
        return command.hasNext("delete");
    }

    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of book which you want to delete");
        Long bookId = in.nextLong();
        bookService.deleteBook(bookId);
        System.out.println("Book was marked as deleted");
    }
}
