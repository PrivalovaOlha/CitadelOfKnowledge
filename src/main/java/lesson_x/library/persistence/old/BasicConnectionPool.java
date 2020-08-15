//package lesson_x.library.persistence;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BasicConnectionPool {
//    private int poolSize = 5;
//    private List<Connection> freeConnections = new ArrayList<>(poolSize);
//    private List<Connection> connectionsInUsing = new ArrayList<>(poolSize);
//    final MysqlDataSource ds = null;
//
//
//    public MysqlDataSource getDs() {
//        return ds;
//    }
//
//    private BasicConnectionPool()  {
//        try {
//            for (int i = 0; i < poolSize; i++) {
//                freeConnections.add(ds.getConnection());
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean returnConnection(Connection connection) {
//        freeConnections.add(connection);
//        return connectionsInUsing.remove(connection);
//    }
//
//    public Connection getConnection() throws InterruptedException {
//        int i = 0;
//        while (freeConnections == null) {
//            Thread.currentThread().wait(100);
//            i++;
//            if (i == 10) {
//                System.out.println("мне мделать свое исключение, что б выводило ошибку");
//            }
//        }
//        Connection connection = freeConnections.remove(poolSize - 1);
//        connectionsInUsing.add(connection);
//        return connection;
//    }
//}
