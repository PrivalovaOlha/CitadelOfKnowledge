package lesson_x.library.domain;

import lombok.*;

import javax.persistence.*;
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "name")
    private String bookName;
    @Column(name = "author")
    private String author;
    @Column(name = "deleted")
    private boolean isDel = false;
    @Column(name = "lost")
    private boolean isLost = false;
}

