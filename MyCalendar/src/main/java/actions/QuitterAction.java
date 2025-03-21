// QuitterAction.java
package actions;

import java.util.Scanner;

import actions.Action;
import main.CalendarManager;

public class QuitterAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        System.out.println("Fermeture de l’application...");
        System.exit(0);
    }

    @Override
    public String nom() {
        return "Quitter";
    }
}
