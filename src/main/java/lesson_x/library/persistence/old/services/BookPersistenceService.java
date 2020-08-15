//package lesson_x.library.persistence.services;
//
//import lesson_x.library.domain.Book;
//import lesson_x.library.persistence.StatementExecutor;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookPersistenceService implements IBookPersistenceService {
//    private static BookPersistenceService instance;
//
//    public static synchronized BookPersistenceService getInstance() {
//        if (instance == null) {
//            instance = new BookPersistenceService();
//        }
//        return instance;
//    }
//
//    @Override
//    public List<Book> getAll() {
//
//        return StatementExecutor.executeSql("SELECT book_id,name,author,lost,deleted FROM book", (preparedStatement) -> {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Book> allBooks = new ArrayList<Book>();
//            while (resultSet.next()) {
//                 Book book = new Book();
//                book.setId(resultSet.getLong(1));
//                book.setBookName(resultSet.getString(2));
//                book.setAuthor(resultSet.getString(3));
//                if (resultSet.getInt(4) == 1) {
//                    book.setLost(true);
//                } else {
//                    book.setLost(false);
//                }
//                if (resultSet.getInt(5) == 1) {
//                    book.setDel(true);
//                } else {
//                    book.setDel(false);
//                }
//                allBooks.add(book);
//            }
//            return allBooks;
//        });
//    }
//
//    @Override
//    public Book save(Book book) {
//        if (book.getId() == null) {
//            Long id = addInDB(book);
//            return getById(id);
//        } else {
//            return updateInDB(book);
//        }
//    }
//
//    @Override
//    public Book getById(Long id) {
//        final Book book = new Book();
//        String sql = "SELECT book_id,name,author,lost,deleted FROM book WHERE book_id = ?";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            Integer bookIdi = Math.toIntExact(id);
//            preparedStatement.setInt(1, bookIdi);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                book.setId(resultSet.getLong(1));
//                book.setBookName(resultSet.getString(2));
//                book.setAuthor(resultSet.getString(3));
//                if (resultSet.getInt(4) == 1) {
//                    book.setLost(true);
//                } else {
//                    book.setLost(false);
//                }
//                if (resultSet.getInt(5) == 1) {
//                    book.setDel(true);
//                } else {
//                    book.setDel(false);
//                }
//            }
//            return book;
//        });
//    }
//
//    @Override
//    public void delete(Book book) {
//        delete(book.getId());
//    }
//
//    @Override
//    public void delete(Long id) {
//        String sql = "UPDATE book SET deleted = 1 WHERE book_id =?";
//        StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setInt(1, Math.toIntExact(id));
//            preparedStatement.executeUpdate();
//        });
//
//    }
//
//    public Long addInDB(Book book) {
//
//        String sql = "INSERT INTO book(name,author) VALUES (?,?)";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setString(1, book.getBookName());
//            preparedStatement.setString(2, book.getAuthor());
//            if (preparedStatement.executeUpdate() == 0) {
//                throw new RuntimeException("Запись не добавлена");
//            }
//            return StatementExecutor.executeSql("SELECT * FROM book  ORDER BY book_id DESC LIMIT 0, 1", (preparedStatement1) -> {
//                ResultSet resultSet = preparedStatement1.executeQuery();
//                resultSet.next();
//                return resultSet.getLong(1);
//            });
//        });
//    }
//
//
//    public Book updateInDB(Book book) {
//        String sql = "UPDATE book SET name = ?,author =?,lost =?,deleted =? WHERE book_id = ?";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            Integer bookIdi = Math.toIntExact(book.getId());
//
//            preparedStatement.setString(1, book.getBookName());
//            preparedStatement.setString(2, book.getAuthor());
//            if (book.isLost()) {
//                preparedStatement.setInt(3, 1);
//            } else {
//                preparedStatement.setInt(3, 0);
//            }
//            if (book.isDel()) {
//                preparedStatement.setInt(4, 1);
//            } else {
//                preparedStatement.setInt(4, 0);
//            }
//            preparedStatement.setInt(5, bookIdi);
//            preparedStatement.executeUpdate();
//            return getById(book.getId());
//        });
//    }
//
//
//}
