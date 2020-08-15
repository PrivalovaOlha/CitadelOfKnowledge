package lesson_x.library.persistence.services;

import lesson_x.library.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookHistoryPersistenceService extends JpaRepository<BookHistory,Long > {

}
