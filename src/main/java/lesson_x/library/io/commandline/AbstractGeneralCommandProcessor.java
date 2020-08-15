package lesson_x.library.io.commandline;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractGeneralCommandProcessor implements GeneralCommandProcessor {

    private final String command;
    private final List<? extends CommandProcessor> processors;

    protected AbstractGeneralCommandProcessor(String command, List<? extends CommandProcessor> processors) {
        this.command = command;
        this.processors = processors;
    }

    public final boolean canProcess(Scanner scanner) {
        return scanner.hasNext(command);
    }

    public final void defineCommand(Scanner command) {
        command.next();
        for (CommandProcessor processor : processors) {
            if (processor.canProcess(command)) {
                processor.defineCommand(command);
                break;
            }
        }
    }

}
//
//class Child2 extends Parent {
//    public Child2() {
//        super("dsgfdhh");
//        System.out.println("create form child 2");
//    }
//}
//
//class Child extends Parent {
//    public Child() {
//        super("чаывпр");
//        System.out.println("create from child");
//    }
//}
//
//class Parent extends Object {
//
//    private final String otKogo;
//
//    public Parent(String otKogo) {
//        System.out.println("create from Parent.  " + otKogo);
//        this.otKogo = otKogo;
//    }
//}
//
//class Main {
//    public static void main(String[] args) {
//        Parent parent = new Child();
//        Parent parent2 = new Child2();
//        System.out.println("finish");
//
//    }
//}