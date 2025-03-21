// SupprimerEvenementAction.java
package actions;

import main.CalendarManager;

import java.util.Scanner;

public class SupprimerEvenementAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        System.out.print("ID de l’événement à supprimer : ");
        String id = scanner.nextLine();
        calendar.events.removeById(id);
        System.out.println("Événement supprimé (si ID valide).");
    }

    @Override
    public String nom() {
        return "Supprimer un événement";
    }
}
