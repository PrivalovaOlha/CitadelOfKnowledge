//package lesson_x.library.persistence;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public final class PersistenceServiceProvider {
//    private static final PersistenceServiceProvider INSTANCE = new PersistenceServiceProvider();
//
//    private final Map<Class, Object> services = new HashMap<>();
//
//    public static <T> T getService(Class<T> service) {
//        return (T) INSTANCE.services.get(service);
//    }
//
//    private PersistenceServiceProvider() {
//        //  services.put(IBookHistoryPersistenceService.class, new BookHistoryPersistenceService());
////        services.put(IBookPersistenceService.class, new BookPersistenceService());
//        //       services.put(ICustomerPersistenceService.class, new CustomerPersistenceService());
//    }
//}
