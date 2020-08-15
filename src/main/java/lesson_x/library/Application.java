package lesson_x.library;

import lesson_x.library.io.commandline.CommandProcessor;
import lesson_x.library.io.commandline.GeneralCommandProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class Application {

//history -take <cusId> <bookId>
//customer -a <name>
//book -a
    // Please, enter name :
    // Please, enter author :
//book -e <id>
    // Please, enter name (Press enter if no changes) <Current>:
    // Please, enter author (Press enter if no changes) <Current>:

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        Map<String, GeneralCommandProcessor> beans = context.getBeansOfType(GeneralCommandProcessor.class);
        Collection<GeneralCommandProcessor> processors = beans.values();

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("Please enter a command: ");

            Scanner command = new Scanner(input.nextLine());

            boolean handled = false;
            for (CommandProcessor processor : processors) {
                if (processor.canProcess(command)) {
                    handled = true;
                    processor.defineCommand(command);
                    break;
                }
            }

            if (!handled) {
                System.err.println("Unknown command! " + command.nextLine());
            }
        }
    }
}
/**
 * Обновление клиентов
 * !! Нужен ли теперь метод гет вообще,
 * Вывод без удаленных клиентов /книг
 * Вывод всех клиентов
 * Вывод только удаленных клиентов/книг
 * Вывод истории по клиентам:
 * - пометка если книга удалена или утеряна
 * - пометка если клиент удален
 * Отформатировать вываод красивым
 * <p>
 * Update bookHistory when the book is lost
 * Книга потеряна, надо ли проставлять ретурн дейт для целосности данных?
 */