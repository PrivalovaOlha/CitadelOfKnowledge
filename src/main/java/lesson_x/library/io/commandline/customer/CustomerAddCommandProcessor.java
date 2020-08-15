package lesson_x.library.io.commandline.customer;

import lesson_x.library.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerAddCommandProcessor implements CustomerRelatedCommandProcessor {
    private final CustomerService customerService;

    @Autowired
    public CustomerAddCommandProcessor(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean canProcess(Scanner command) {
        return command.hasNext("add");
    }

    @Override
    public void defineCommand(Scanner command) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input name of customer");
        String name = in.nextLine();
        System.out.println("Input customer`s year of birth");
        int ageYear = in.nextInt();
        System.out.println("Input month and day of customer`s birth");
        int ageMonth = in.nextInt();
        int ageDay = Integer.parseInt(in.nextLine().trim());
        System.out.println("Input address of customer");
        String address = in.nextLine();
        System.out.println("Input phone number of customer");
        String phoneNum = in.nextLine();
        customerService.addCustom(name, ageYear, ageMonth, ageDay, address, phoneNum);
        System.out.println("New customer was added");
    }
}
