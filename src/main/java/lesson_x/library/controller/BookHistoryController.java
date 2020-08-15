package lesson_x.library.controller;

import lesson_x.library.domain.BookHistory;
import lesson_x.library.services.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "BookHistory", produces = "application/json;charset=UTF-8")
public class BookHistoryController {

    private BookHistoryService bookHistoryService;

    @Autowired
    public BookHistoryController(BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @RequestMapping(value = "/{userId}")
    @ResponseBody
    public List<BookHistory> display(@PathVariable Long userId) {
        return bookHistoryService.getByBookId(userId);
    }
}
