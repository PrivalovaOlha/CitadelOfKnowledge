package lesson_x.library.io.commandline.customer;

import lesson_x.library.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerEditCommandProcessor implements CustomerRelatedCommandProcessor {
    private final CustomerService customerService;

    @Autowired
    public CustomerEditCommandProcessor(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("edit");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of customer which you want to edit");
        Long customerId = Long.parseLong(in.nextLine().trim());
        System.out.println("Enter new name for this customer(or press enter to skip)" + "\n" +
                "Current name: " + customerService.getCustomer(customerId).getCustomerName());
        String newName = in.nextLine();
        if (!newName.isEmpty()) {
            customerService.changeCustomerName(customerId, newName);
        }
        System.out.println("Enter new address for the customer(or press enter to skip)" + "\n" +
                "Current adress: " + customerService.getCustomer(customerId).getCustomerAddress());
        String newAddress = in.nextLine();
        if (!newAddress.isEmpty()) {
            customerService.changeCustomerAddress(customerId, newAddress);
        }
        System.out.println("Enter new age for the customer(or press enter to skip)" + "\n" +
                "Current age: " + customerService.getCustomer(customerId).getCustomerAge());
        int newAgeYear = in.nextInt();
        int newAgeMonth = in.nextInt();
        int newAgeDay = in.nextInt();
        customerService.changeCustomerAge(customerId, newAgeYear, newAgeMonth, newAgeDay);
        System.out.println("The customer was updated successfully");
    }
}
