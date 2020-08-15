package lesson_x.library.io.commandline;

import lesson_x.library.io.commandline.bookHistory.BookHistoryRelatedCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class HistoryCommandProcessor extends AbstractGeneralCommandProcessor {

    @Autowired
    public HistoryCommandProcessor(List<BookHistoryRelatedCommandProcessor> processors) {
        super("bookHistory", processors);
    }
}
