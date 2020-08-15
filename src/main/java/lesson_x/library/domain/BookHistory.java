package lesson_x.library.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer_story")
public class BookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "takenDate")
    private LocalDate takeDate;
    @Column(name = "returnDate")
    private LocalDate returnDate;
    @Column(name = "lost")
    private LocalDate lost;


}
