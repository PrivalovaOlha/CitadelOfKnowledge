//package lesson_x.library.persistence.services;
//
//import lesson_x.library.domain.Customer;
//import lesson_x.library.persistence.PersistenceUtils;
//import lesson_x.library.persistence.StatementExecutor;
//
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerPersistenceService implements ICustomerPersistenceService {
//    private static CustomerPersistenceService instance;
//
//   /* public static CustomerPersistenceService getIntance() {
//        if (instance == null) {
//            instance = new CustomerPersistenceService();
//        }
//        return instance;
//    }*/
//
//    @Override
//    public List<Customer> findAll() {
//        List<Customer> allCustomers = new ArrayList<>();
//
//        String sql = "SELECT customer_id,age,name,address,phone_num,deleted FROM customer";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Customer customer = new Customer();
//                customer.setId(resultSet.getLong(1));
//                customer.setCustomerAge(new java.sql.Date(resultSet.getDate(2).getTime()).toLocalDate());
//                customer.setCustomerName(resultSet.getString(3));
//                customer.setCustomerAddress(resultSet.getString(4));
//                customer.setCustomerAddress(resultSet.getString(5));
//                if (resultSet.getInt(6) == 1) {
//                    customer.setDeletedCustom(true);
//                } else {
//                    customer.setDeletedCustom(false);
//                }
//                allCustomers.add(customer);
//            }
//            return allCustomers;
//        });
//    }
//
//
//    @Override
//    public Customer save(Customer customer) {
//        if (customer.getId() == null) {
//            Long id = addCustomer(customer);
//            return findById(id);
//        } else {
//            return updateInDB(customer);
//        }
//    }
//
//    @Override
//    public Customer findById(Long id) {
//        Customer customer = new Customer();
//        String sql = "SELECT customer_id,age,name,address,phone_num,deleted FROM customer WHERE customer_id = ?";
//        Integer customerIdi = Math.toIntExact(id);
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setInt(1, customerIdi);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                customer.setId(resultSet.getLong(1));
//                customer.setCustomerAge(PersistenceUtils.toLocalDate(resultSet.getDate(2)));
//                customer.setCustomerName(resultSet.getString(3));
//                customer.setCustomerAddress(resultSet.getString(4));
//                customer.setUserPhoneNum(resultSet.getString(5));
//                if (resultSet.getInt(6) == 1) {
//                    customer.setDeletedCustom(true);
//                } else {
//                    customer.setDeletedCustom(false);
//                }
//            }
//            return customer;
//        });
//
//    }
//
//    @Override
//    public void delete(Customer customer) {
//        delete(customer.getId());
//    }
//
//    @Override
//    public void delete(Long id) {
//        String sql = "UPDATE customer SET deleted = 1 WHERE customer_id = ?";
//        Integer customerIdi = Math.toIntExact(id);
//        StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setInt(1, customerIdi);
//            preparedStatement.executeUpdate();
//        });
//    }
//
//    public Long addCustomer(Customer customer) {
//        String sql = "INSERT INTO customer(name,age,address,phone_num) VALUES (?,?,?,?)";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setString(1, customer.getCustomerName());
//            preparedStatement.setDate(2, Date.valueOf(customer.getCustomerAge()));
//            preparedStatement.setString(3, customer.getCustomerAddress());
//            preparedStatement.setString(4, customer.getUserPhoneNum());
//
//            if (preparedStatement.executeUpdate() == 0) {
//                throw new RuntimeException("Пользователь в базу не добавлен");
//            }
//            return StatementExecutor.executeSql("SELECT * FROM customer ORDER BY customer_id DESC LIMIT 0,1", (preparedStatement1) -> {
//                ResultSet resultSet = preparedStatement1.executeQuery();
//                resultSet.next();
//                return resultSet.getLong(1);
//            });
//        });
//
//    }
//
//    public Customer updateInDB(Customer customer) {
//        Integer customerIdi = Math.toIntExact(customer.getId());
//        String sql = "UPDATE customer SET name = ?,address =?, phone_num = ?, age = ? WHERE customer_id = ?";
//        return StatementExecutor.executeSql(sql, (preparedStatement) -> {
//            preparedStatement.setString(1, customer.getCustomerName());
//            preparedStatement.setString(2, customer.getCustomerAddress());
//            preparedStatement.setString(3, customer.getUserPhoneNum());
//            preparedStatement.setDate(4, Date.valueOf(customer.getCustomerAge()));
//            preparedStatement.setInt(5, customerIdi);
//            preparedStatement.executeUpdate();
//            return findById(customer.getId());
//        });
//
//    }
//
//}
//


