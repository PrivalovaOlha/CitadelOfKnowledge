package lesson_x.library.io.commandline;

import lesson_x.library.io.commandline.book.BookRelatedCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class BookCommandProcessor extends AbstractGeneralCommandProcessor {
    @Autowired
    public BookCommandProcessor(List<BookRelatedCommandProcessor> processors) {
        super("book", processors);
    }
}

//
//class A {
//    public static void main(String[] args) {
//        Class<A> clazz = A.class;
//        Class<Autowired> autClass = Autowired.class;
//
//        //этап 1
//        BookCommandProcessor processor = new BookCommandProcessor();
//
//        //этап 2
//        Field[] fields = BookCommandProcessor.class.getDeclaredFields();
//        for (Field f : fields) {
//            Autowired annotation = null;
//
//            Annotation[] allAnnotations = f.getAnnotations();
//            for (Annotation ann : allAnnotations) {
//                if (ann.getClass() == Autowired.class) {
//                    annotation = (Autowired) ann;
//                    break;
//                }
//            }
//
//            if (annotation != null) {
//                Object bean = context.getBeansOfType(f.getType());
//                f.set(processor,bean);
//            }
//        }
//    }
//}