package lesson_x.library.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "name")
    private String customerName;
    @Column(name = "age")
    private LocalDate customerAge;
    @Column(name = "address")
    private String customerAddress;
    @Column(name = "deleted")
    private boolean deletedCustom = false;
    @Column(name = "phone_num")
    private String userPhoneNum;
}
