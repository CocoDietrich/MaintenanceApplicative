// AjouterEvenementAction.java
package actions;

import event_types.*;
import main.CalendarManager;
import value_objects.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AjouterEvenementAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        System.out.println("--- Ajouter un événement ---");
        System.out.println("1 - RDV Personnel");
        System.out.println("2 - Réunion");
        System.out.println("3 - Événement périodique");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> ajouterRDV(calendar, scanner);
            case "2" -> ajouterReunion(calendar, scanner);
            case "3" -> ajouterPeriodique(calendar, scanner);
            default -> System.out.println("Type d'événement inconnu.");
        }
    }

    @Override
    public String nom() {
        return "Ajouter un événement";
    }

    private void ajouterRDV(CalendarManager calendar, Scanner scanner) {
        TitreEvenement titre = demanderTitre(scanner);
        DateEvenement date = demanderDate(scanner);
        DureeEvenement duree = demanderDuree(scanner);
        calendar.ajouterEvent(new RdvPersonnel(titre, date, duree));
        System.out.println("RDV personnel ajouté.");
    }

    private void ajouterReunion(CalendarManager calendar, Scanner scanner) {
        TitreEvenement titre = demanderTitre(scanner);
        DateEvenement date = demanderDate(scanner);
        DureeEvenement duree = demanderDuree(scanner);
        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();
        Participants participants = Participants.collecter(scanner);
        calendar.ajouterEvent(new Reunion(titre, date, duree, lieu, participants));
        System.out.println("Réunion ajoutée.");
    }

    private void ajouterPeriodique(CalendarManager calendar, Scanner scanner) {
        TitreEvenement titre = demanderTitre(scanner);
        DateEvenement date = demanderDate(scanner);
        DureeEvenement duree = demanderDuree(scanner);
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());
        calendar.ajouterEvent(new EvenementPeriodique(titre, date, duree, new FrequenceJours(frequence)));
        System.out.println("Événement périodique ajouté.");
    }

    private TitreEvenement demanderTitre(Scanner scanner) {
        System.out.print("Titre : ");
        return new TitreEvenement(scanner.nextLine());
    }

    private DateEvenement demanderDate(Scanner scanner) {
        System.out.println("Date de l’événement : ");
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

    private DureeEvenement demanderDuree(Scanner scanner) {
        System.out.print("Durée (en minutes) : ");
        return new DureeEvenement(Integer.parseInt(scanner.nextLine()));
    }
}
