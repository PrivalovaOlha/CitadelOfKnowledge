package lesson_x.library.io.commandline.customer;

import lesson_x.library.StringUtils;
import lesson_x.library.domain.Customer;
import lesson_x.library.io.commandline.CommandProcessor;
import lesson_x.library.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerListCommandProcessor implements CustomerRelatedCommandProcessor {
    private final CustomerService customerService;
    private static final String SPACES = StringUtils.repeat(30, StringUtils.SPACE);

    @Autowired
    public CustomerListCommandProcessor(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean canProcess(Scanner scanner) {
        return scanner.hasNext("list");
    }

    @Override
    public void defineCommand(Scanner scanner) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 'yes' if you want to see the list of active customers(or press enter to skip)");
        String activeC = in.nextLine();
        if (!activeC.isEmpty()) {
            customerService.getActiveCustomers().stream().forEach(n -> System.out.println(n));
            System.out.println("List is over");
        }
        System.out.println();
        System.out.println("Enter 'yes' if you want to see the list of all customers(or press enter to skip)");
        String allC = in.nextLine();
        if (!allC.isEmpty()) {
            customerService.getAllCustomer().stream().forEach(n -> System.out.println(n));
            System.out.println("List is over");
        }
        System.out.println();
        System.out.println("Enter 'yes' if you want to see the list of deleted customers(or press enter to skip)");
        String delC = in.nextLine();
        if (!delC.isEmpty()) {
            customerService.getDeletedCustomers().stream().forEach(n -> print(n));
            System.out.println("List is over");
        }
    }

    public void print(Customer customer) {
        System.out.println("|" + ("Customer`s id" + SPACES).substring(0, 30) +
                "|" + ("Customer`s Name" + SPACES).substring(0, 30) +
                "|" + ("Customer`s age" + SPACES).substring(0, 30) +
                "|" + ("Customer`s address" + SPACES).substring(0, 30) +
                "|" + ("Customer`s phone mumber" + SPACES).substring(0, 30) +
                "|" + ("Customer is deleted" + SPACES).substring(0, 30));
        String del = " ";
        if (customer.isDeletedCustom()) {
            del = "deleted";
        }
        System.out.println(
                "| " + (customer.getId() + SPACES).substring(0, 30) +
                        "| " + (customer.getCustomerName() + SPACES).substring(0, 30) +
                        "| " + (customer.getCustomerAge() + SPACES).substring(0, 30) +
                        "| " + (customer.getCustomerAddress() + SPACES).substring(0, 30) +
                        "| " + (customer.getUserPhoneNum() + SPACES).substring(0, 30) +
                        "| " + (del + SPACES).substring(0, 30) +
                        " |");

    }

}
