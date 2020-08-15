package lesson_x.library.persistence.old;

import java.sql.Date;
import java.time.LocalDate;

public final class PersistenceUtils {

    public static LocalDate toLocalDate(Date original) {
        if (original == null) {
            return null;
        } else {
            return original.toLocalDate();
        }
    }

    private PersistenceUtils() {
        throw new UnsupportedOperationException("can't be instantiated");
    }
}
