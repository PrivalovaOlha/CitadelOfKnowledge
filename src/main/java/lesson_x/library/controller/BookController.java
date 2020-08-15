package lesson_x.library.controller;

import lesson_x.library.domain.Book;
import lesson_x.library.domain.BookHistory;
import lesson_x.library.domain.Customer;
import lesson_x.library.services.BookHistoryService;
import lesson_x.library.services.BookService;
import lesson_x.library.services.CustomerService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "Book", consumes = APPLICATION_JSON_VALUE + ";charset=UTF-8", produces = APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class BookController {

    private BookHistoryService bookHistoryService;
    private BookService bookService;
    private CustomerService customerService;

    @Autowired
    public BookController(BookHistoryService bookHistoryService, BookService bookService, CustomerService customerService) {
        this.bookHistoryService = bookHistoryService;
        this.bookService = bookService;
        this.customerService = customerService;
    }


    @RequestMapping(method = POST, value = "edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void editBook(
            @RequestBody Map<String, String> map) {
        Long bookId = Long.parseLong(map.get("bookId"));
        bookService.updateBookName(bookId, map.get("bookName"));
        bookService.updateAuthor(bookId, map.get("bookAuthor"));
    }

    @RequestMapping(method = GET, value = "delete/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @RequestMapping(method = POST, value = "display")
    @ResponseBody
    public List<Book> display() {
        // HttpHeaders headers = new HttpHeaders();
        //   headers.add("Content-Type", "application/json");
        //headers.add("Access-Control-Allow-Origin", "*");
        List<Book> books = bookService.getAllBooks();
        System.out.println(books.get(1));
        return books;
    }

    @RequestMapping(method = POST, value = "add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void addBook(
            @RequestBody Map<String, String> map) {
        bookService.addBook(map.get("bookName"), map.get("bookAuthor"));

    }

    protected void displayUserStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {

        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        try (PrintWriter out = response.getWriter()) {
            List<BookHistory> list = bookHistoryService.getByCustId(Long.parseLong(request.getParameter("userId")));
            JSONArray Jlist = new JSONArray(list);
            out.print(Jlist);
        }
    }
}
