package lesson_x.library.persistence.old;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StatementExecutor {

    private static MysqlDataSource dataSource = new MysqlDataSource() {{
        setURL("jdbc:mysql://localhost:3306/library");
        setUser("root");
        setPassword("root");
    }};


    public static void executeSql(String sql, SqlExceptionSafeConsumer<PreparedStatement> statementConsumer) {
        //грязный трюк чтобы избежать необходимости писать return null в void методах
        executeSql(sql, (preparedStatement) -> {
            statementConsumer.consume(preparedStatement);
            return null;
        });
    }

    public static <T> T executeSql(String sql, SqlExceptionSafeFunction<PreparedStatement, T> statementFunction) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                T result = statementFunction.apply(preparedStatement);
                connection.commit();
                return result;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }

    //  private StatementExecutor() {
    //  }

    /**
     * @param <P> parameter type
     * @param <R> return type
     */
    public interface SqlExceptionSafeFunction<P, R> {
        R apply(P param) throws SQLException;
    }

    /**
     * @param <P> parameter type
     */
    public interface SqlExceptionSafeConsumer<P> {
        void consume(P param) throws SQLException;
    }
}

//class SQLText {
//    public static void main(String[] args) throws SQLException {
//        final CustomerService customerService = CustomerService.getInstance();
//        CustomerPersistenceService tmp = new CustomerPersistenceService();
//        //System.out.println( tmp.getCustomer(2L));
//        //  tmp.updateAddressInDB(1L, "Пр-т Свободы 5");
//        // tmp.updateAgeInDB(1L, "1997.11.13");
//        //tmp.updateNameInDB(1L, "Ковров Людовик Петрович");
//        // System.out.println(tmp.addCustomer(customerService.getCustomer(1L)));
//        tmp.updateInDB(customerService.getCustomer(2L));
//        System.out.println(tmp.getCustomer(1L));
//
//    }
//}
