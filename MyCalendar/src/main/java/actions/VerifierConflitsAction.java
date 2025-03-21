// VerifierConflitsAction.java
package actions;

import main.CalendarManager;

import java.util.Scanner;

public class VerifierConflitsAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        boolean conflits = calendar.events.detectConflicts();
        if (!conflits) {
            System.out.println("Aucun conflit détecté.");
        }
    }

    @Override
    public String nom() {
        return "Vérifier les conflits";
    }
}
