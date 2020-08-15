package lesson_x.library.io.commandline.book;

import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookAddCommandProcessor implements BookRelatedCommandProcessor {

    private final BookService bookService;

    @Autowired
    public BookAddCommandProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean canProcess(Scanner command) {
        return command.hasNext("add");
    }

    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input name of book: ");
        String name = in.nextLine();
        System.out.println("Input author of book: ");
        String author = in.nextLine();
        bookService.addBook(name, author);
        System.out.println("A book added successfully");
    }
}
