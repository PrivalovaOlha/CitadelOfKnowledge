package lesson_x.library.io.commandline.book;

import lesson_x.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookEditCommandProcessor implements BookRelatedCommandProcessor {

    private final BookService bookService;

    @Autowired
    public BookEditCommandProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean canProcess(Scanner command) {
        return command.hasNext("edit");
    }

    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of book which you want to edit");
        Long bookId = Long.parseLong(in.nextLine().trim());
        System.out.println("Enter new author for this book (or press enter to skip)" + "\n" +
                "Current author: " + bookService.getBook(bookId).getAuthor());
        String newAuthor = in.nextLine();
        if (!newAuthor.isEmpty()) {
            bookService.updateAuthor(bookId, newAuthor);
        }

        System.out.println("Enter new name for the book(or press enter to skip)" + "\n" +
                "Current name: " + bookService.getBook(bookId).getBookName());
        String newName = in.nextLine();
        if (!newName.isEmpty()) {
            bookService.updateBookName(bookId, newName);
        }

        System.out.println("The book was updated successfully");
    }
}
