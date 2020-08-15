package lesson_x.library.persistence.services;

import lesson_x.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookPersistenceService extends JpaRepository<Book, Long> {
}
