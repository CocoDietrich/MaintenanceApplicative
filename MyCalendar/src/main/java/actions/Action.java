package actions;// actions.Action.java

import main.CalendarManager;
import java.util.Scanner;

public interface Action {
    void executer(CalendarManager calendar, Scanner scanner);
    String nom();
}
