package lesson_x.library.io.commandline;

import lesson_x.library.io.commandline.customer.CustomerRelatedCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CustomerCommandProcessor extends AbstractGeneralCommandProcessor {

    @Autowired
    public CustomerCommandProcessor(List<CustomerRelatedCommandProcessor> processors) {
        super("customer", processors);
    }
}
