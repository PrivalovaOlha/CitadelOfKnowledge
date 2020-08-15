package lesson_x.library.io.commandline;

import java.util.Scanner;

public interface CommandProcessor {
    boolean canProcess(Scanner command);

    void defineCommand(Scanner command);
}
