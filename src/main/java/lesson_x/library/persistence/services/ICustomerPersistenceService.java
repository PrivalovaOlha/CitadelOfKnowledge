package lesson_x.library.persistence.services;

import lesson_x.library.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerPersistenceService extends JpaRepository<Customer,Long > {
}
