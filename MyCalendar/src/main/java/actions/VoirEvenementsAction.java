// VoirEvenementsAction.java
package actions;

import java.util.Scanner;

import main.CalendarManager;

public class VoirEvenementsAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        calendar.afficherEvenements();
    }

    @Override
    public String nom() {
        return "Voir les événements";
    }
}
