// VoirEvenementsPeriodeAction.java
package actions;

import main.CalendarManager;
import value_objects.DateEvenement;

import java.time.LocalDateTime;
import java.util.Scanner;

public class VoirEvenementsPeriodeAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        System.out.println("--- Événements dans une période ---");
        DateEvenement debut = demanderDate(scanner, "début");
        DateEvenement fin = demanderDate(scanner, "fin");
        calendar.eventsDansPeriode(debut, fin).displayEvents();
    }

    @Override
    public String nom() {
        return "Voir événements dans une période";
    }

    private DateEvenement demanderDate(Scanner scanner, String label) {
        System.out.println("Date de " + label + " :");
        System.out.print("Année : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois : ");
        int mois = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour : ");
        int jour = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute : ");
        int minute = Integer.parseInt(scanner.nextLine());
        return new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
    }
}
