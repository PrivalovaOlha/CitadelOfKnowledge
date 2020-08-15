package lesson_x.library.persistence.old;

import java.util.List;

/**
 * @param <I> Id type
 * @param <T> Object type
 */
public interface PersistenceRepository<I extends Number, T> {

    List<T> findAll();
    //add
    //update
    T save(T object);

    T findById(I id);

    void delete(T object);

    void delete(I id);
}
