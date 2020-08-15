package lesson_x.library.services;

import lesson_x.library.domain.Customer;
import lesson_x.library.persistence.services.ICustomerPersistenceService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {
    private static CustomerService instance;
    private final ICustomerPersistenceService repository;

    public CustomerService(ICustomerPersistenceService repository) {
        this.repository = repository;
    }

    // public static CustomerService getInstance() {
    //     if (instance == null) {
    //         instance = new CustomerService();
    //     }
    //     return instance;
    // }
    public void addCustom(String name, int ageYear, int ageMonth, int ageDay, String address, String phoneNum) {
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setCustomerAge(LocalDate.of(ageYear, ageMonth, ageDay));
        customer.setCustomerAddress(address);
        customer.setUserPhoneNum(phoneNum);
        repository.save(customer);
    }


    public Customer getCustomer(Long customerId) {
        return repository.findById(customerId).orElse(null);
    }

    public List<Customer> getAllCustomer() {
        return repository.findAll();
    }

    public void deleteCustomer(Long id) {
        repository.delete(getCustomer(id));
    }

    public void changeCustomerName(Long id, String newName) {
        Customer customer = getCustomer(id);
        customer.setCustomerName(newName);
        update(customer);
    }

    public void changeCustomerAge(Long id, int ageYear, int ageMonth, int ageDay) {
        Customer customer = getCustomer(id);
        customer.setCustomerAge(LocalDate.of(ageYear, ageMonth, ageDay));
        update(customer);
    }

    public void changeCustomerAddress(Long id, String address) {
        Customer customer = getCustomer(id);
        customer.setCustomerAddress(address);
        update(customer);
    }

    public void update(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> getActiveCustomers() {
        return getAllCustomer().stream()
                .filter(cust -> !cust.isDeletedCustom())
                .collect(Collectors.toList());
    }

    public List<Customer> getDeletedCustomers() {
        return getAllCustomer().stream()
                .filter(Customer::isDeletedCustom)
                .collect(Collectors.toList());
    }
}
