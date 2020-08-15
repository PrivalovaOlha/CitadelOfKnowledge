//package lesson_x.library.persistence.services;
//
//import lesson_x.library.domain.BookHistory;
//import lesson_x.library.persistence.PersistenceUtils;
//import lesson_x.library.persistence.StatementExecutor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapperResultSetExtractor;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class BookHistoryPersistenceService implements IBookHistoryPersistenceService {
//    //to delete since 02.03.2020
////    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//    //  private static BookHistoryPersistenceService instance;
//
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Override
//    public List<BookHistory> findAll() {
//        String sql = "SELECT story_id,book_id,customer_id,lost,takenDate,returnDate FROM customer_story" /*WHERE returnDate IS null and lost IS NOT null"*/;
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            List<BookHistory> allTaken = new ArrayList<>();
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                BookHistory bookHistory = new BookHistory();
//                bookHistory.setId(resultSet.getLong(1));
//                bookHistory.setBookId(resultSet.getLong(2));
//                bookHistory.setCustomerId(resultSet.getLong(3));
//                bookHistory.setLost(PersistenceUtils.toLocalDate(resultSet.getDate(4)));
//                bookHistory.setTakeDate(PersistenceUtils.toLocalDate(resultSet.getDate(5)));
//                bookHistory.setReturnDate(PersistenceUtils.toLocalDate(resultSet.getDate(6)));
//                allTaken.add(bookHistory);
//            }
//            return allTaken;
//        });
//    }
//
//    @Override
//    public BookHistory save(BookHistory bookHistory) {
//        try {
//            if (bookHistory.getId() != null) {
//                return update(bookHistory);
//            } else {
//                Long id = add(bookHistory);
//                return findById(id);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private Long add(BookHistory newHistory) throws SQLException {
//        String sql = "INSERT INTO customer_story(book_id,customer_id,takenDate) VALUES (:book_id,:customer_id,:takenDate)";
//        Map<String, Object> mapSQLParam = new HashMap<>();
//        mapSQLParam.put("bookId", newHistory.getBookId());
//        mapSQLParam.put("customerId", newHistory.getCustomerId());
//        mapSQLParam.put("takenDate", Date.valueOf(LocalDate.now()));
//        namedParameterJdbcTemplate.update(sql, mapSQLParam);
//        sql = "SELECT LAST_INSERT_ID()";
//        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        final Long[] l = new Long[1];
//        namedParameterJdbcTemplate.query(sql, new RowMapperResultSetExtractor<Long>((rs, rowNum) -> l[0] = rs.getLong(1)));
//        System.out.println(l[0]);
//        return l[0];
//
//    }
//
//
//    //     String sql = "INSERT INTO customer_story(book_id,customer_id,takenDate) VALUES (?,?,?)";
//    //     return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//    //         int bookIdi = Math.toIntExact(newHistory.getBookId());
//    //         preparedStatement.setInt(1, bookIdi);
//    //         int customerIdi = Math.toIntExact(newHistory.getCustomerId());
//    //         preparedStatement.setInt(2, customerIdi);
//    //         preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
//    //         if (preparedStatement.executeUpdate() == 0) {
//    //             throw new RuntimeException("Запись не добавлена");
//    //         }
//
//    //         return StatementExecutor.executeSql("SELECT LAST_INSERT_ID()", (preparedStatement1) -> {
//    //             ResultSet resultSet = preparedStatement1.executeQuery();
//    //             resultSet.next();
//    //             return resultSet.getLong(1);
//    //         });
//    //     });
//
//
//    public BookHistory findById(Long bookId) {
//        String sql = "select * from books where id = :id";
//        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", bookId),
//                BookHistory.class);
//
//    }
////  @Override
////  public BookHistory getById(Long bookId) {
////      String sql = "SELECT story_id,book_id,customer_id,lost,takenDate,returnDate FROM customer_story WHERE book_id = ? and returnDate IS null";
////      return StatementExecutor.executeSql(sql, (preparedStatement) -> {
////          int bookIdi = Math.toIntExact(bookId);
////          preparedStatement.setInt(1, bookIdi);
////          ResultSet resultSet = preparedStatement.executeQuery();
////          BookHistory bookHistory = new BookHistory();
//
////          if (resultSet.next()) {
////              bookHistory.setId(resultSet.getLong(1));
////              bookHistory.setBookId(resultSet.getLong(2));
////              bookHistory.setCustomerId(resultSet.getLong(3));
////              if (resultSet.getDate(4) != null) {
////                  bookHistory.setLost(new java.sql.Date(resultSet.getDate(4).getTime()).toLocalDate());
////              }
////              bookHistory.setTakeDate(new java.sql.Date(resultSet.getDate(5).getTime()).toLocalDate());
////              if (resultSet.getDate(4) != null) {
//
////                  bookHistory.setTakeDate(new java.sql.Date(resultSet.getDate(6).getTime()).toLocalDate());
////              }
////          }
////          return bookHistory;
////      });
////  }
//
//    @Override
//    public void delete(BookHistory bookHistory) {
//        delete(bookHistory.getId());
//    }
//
//    @Override
//    public void delete(Long id) {
//
//      /*String sql ="DELETE FROM book_story WHERE story_id = ?";
//        StatementExecutor.executeSql(,(preparedStatement)->{
//        preparedStatement.setInt(1,Math.toIntExact(id));
//        });*/
//    }
//
//
//    private BookHistory update(BookHistory bookHistory) throws SQLException {
//        String sql = "UPDATE customer_story SET book_id = ?,customer_id =?,takenDate =?,returnDate = ?,lost = ? " +
//                "WHERE story_id = ?";
//
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setInt(1, Math.toIntExact(bookHistory.getBookId()));
//            preparedStatement.setInt(2, Math.toIntExact(bookHistory.getCustomerId()));
//            if (bookHistory.getTakeDate() != null) {
//                preparedStatement.setDate(3, Date.valueOf(bookHistory.getTakeDate()));
//            } else {
//                preparedStatement.setDate(3, null);
//            }
//            if (bookHistory.getReturnDate() != null) {
//                preparedStatement.setDate(4, Date.valueOf(bookHistory.getReturnDate()));
//            } else {
//                preparedStatement.setDate(4, null);
//            }
//            if (bookHistory.getLost() != null) {
//                preparedStatement.setDate(5, Date.valueOf(bookHistory.getLost()));
//            } else {
//                preparedStatement.setDate(5, null);
//            }
//            preparedStatement.setInt(6, Math.toIntExact(bookHistory.getId()));
//
//            if (preparedStatement.executeUpdate() == 0) {
//                throw new RuntimeException("Запись в базу не добавлена");
//            }
//            preparedStatement.close();
//            return findById(bookHistory.getId());
//        });
//    }
//
//}
//
//
//