package lesson_x.library.io.commandline.customer;

import lesson_x.library.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerDeleteCommandProcessor implements CustomerRelatedCommandProcessor {
    private final CustomerService customerService;

    @Autowired
    public CustomerDeleteCommandProcessor(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean canProcess(Scanner scanner) {
        return scanner.hasNext("delete");
    }

    @Override
    public void defineCommand(Scanner scanner) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id of customer");
        Long customerId = in.nextLong();
        customerService.deleteCustomer(customerId);
        System.out.println("The customer is marked as deleted");
    }
}
