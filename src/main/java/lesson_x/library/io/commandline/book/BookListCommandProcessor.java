package lesson_x.library.io.commandline.book;

import lesson_x.library.StringUtils;
import lesson_x.library.domain.Book;
import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookListCommandProcessor implements BookRelatedCommandProcessor {
    private static final String SPACES = StringUtils.repeat(30, StringUtils.SPACE);

    private final BookService bookService;

    @Autowired
    public BookListCommandProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean canProcess(Scanner command) {
        return command.hasNext("list");
    }

    public void defineCommand(Scanner command) {
        System.out.println("| " + ("Id of book: " + SPACES).substring(0, 30) +
                "| " + ("Name of book: " + SPACES).substring(0, 30) +
                "| " + ("Name of author: " + SPACES).substring(0, 30) +
                "| " + ("Book is lost: " + SPACES).substring(0, 30) +
                "| " + ("Book is deleted: " + SPACES).substring(0, 30));
        for (Book book : bookService.getAllBooks()) {

            String lost = " ";
            String del = " ";
            if (book.isLost()) {
                lost = " (lost)";
            }
            if (book.isDel()) {
                del = " (deleted)";
            }

            System.out.println(
                    "| " + (book.getId() + SPACES).substring(0, 30) +
                            "| " + (book.getBookName() + SPACES).substring(0, 30) +
                            "| " + (book.getAuthor() + SPACES).substring(0, 30) +
                            "| " + (lost + SPACES).substring(0, 30) + "| " +
                            (del + SPACES).substring(0, 30) +
                            " |");


        }
        System.out.println("List is over");
    }
}
