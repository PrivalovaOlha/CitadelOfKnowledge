package lesson_x.library.controller;

import lesson_x.library.domain.Customer;
import lesson_x.library.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "Customer", produces = "application/json;charset=UTF-8")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = POST, value = "display")
    @ResponseBody
    public List<Customer> display() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(method = POST, value = "edit")
    @ResponseBody
    public void editCustomer(@RequestParam Long userId, @RequestParam String userAge, @RequestParam String userName, @RequestParam String userAddress, @RequestParam String userNum) {
        String[] str = userAge.split(".");
        customerService.changeCustomerAge(userId, Integer.valueOf(str[0]), Integer.valueOf(str[1]), Integer.valueOf(str[2]));
        customerService.changeCustomerName(userId, userName);
        customerService.changeCustomerAddress(userId, userAddress);
    }

    @RequestMapping(method = GET, value = "delete")
    @ResponseBody
    public void deleteUser(@PathVariable Long userId) {
        customerService.deleteCustomer(userId);
    }

    @RequestMapping(method = POST, value = "add")
    @ResponseBody
    public void addUser(@RequestParam Long userId, @RequestParam String userAge, @RequestParam String userName, @RequestParam String userAddress, @RequestParam String userNum) {
        String[] str = userAge.split(".");
        customerService.addCustom(userName, Integer.valueOf(str[0]), Integer.valueOf(str[1]), Integer.valueOf(str[2]), userAddress, userNum);
    }

}
