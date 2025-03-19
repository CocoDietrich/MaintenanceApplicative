import event_types.EvenementPeriodique;
import event_types.RdvPersonnel;
import event_types.Reunion;
import value_objects.*;

import java.time.LocalDateTime;
import java.util.Scanner;

enum MenuActions {
    VOIR_EVENEMENTS("1") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            calendar.afficherEvenements();
        }
    },
    AJOUTER_RDV("2") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            calendar.ajouterEvent(new RdvPersonnel(demanderTitre(scanner), demanderDate(scanner), demanderDuree(scanner)));
            System.out.println("Rendez-vous ajouté.");
        }
    },
    AJOUTER_REUNION("3") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.out.print("Lieu : ");
            String lieu = scanner.nextLine();
            System.out.print("Ajouter un participant (laisser vide pour terminer) : ");
            Participants participants = Participants.collecter(scanner);
            calendar.ajouterEvent(new Reunion(demanderTitre(scanner), demanderDate(scanner), demanderDuree(scanner), lieu, participants));
            System.out.println("Réunion ajoutée.");
        }
    },
    AJOUTER_PERIODIQUE("4") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.out.print("Fréquence (jours) : ");
            FrequenceJours frequence = new FrequenceJours(Integer.parseInt(scanner.nextLine()));
            calendar.ajouterEvent(new EvenementPeriodique(demanderTitre(scanner), demanderDate(scanner), demanderDuree(scanner), frequence));
            System.out.println("Événement périodique ajouté.");
        }
    },
    AFFICHER_PERIODE("5") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.out.print("Date de début (AAAA-MM-JJ HH:mm) : ");
            DateEvenement debut = demanderDate(scanner);
            System.out.print("Date de fin (AAAA-MM-JJ HH:mm) : ");
            DateEvenement fin = demanderDate(scanner);
            calendar.eventsDansPeriode(debut, fin).displayEvents();
        }
    },
    VERIFIER_CONFLITS("6") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            if (!calendar.events.detectConflicts()) {
                System.out.println("Aucun conflit détecté.");
            }
        }
    },
    SUPPRIMER_EVENT("7") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.out.print("Entrez l'ID de l'événement à supprimer : ");
            String id = scanner.nextLine();
            calendar.events.removeById(id);
            System.out.println("Événement supprimé.");
        }
    },
    QUITTER("8") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.exit(0);
        }
    },
    INVALIDE("") {
        @Override
        void executer(CalendarManager calendar, Scanner scanner) {
            System.out.println("Choix invalide, veuillez réessayer.");
        }
    };

    private final String valeur;

    MenuActions(String valeur) {
        this.valeur = valeur;
    }

    abstract void executer(CalendarManager calendar, Scanner scanner);

    public static MenuActions from(String choix) {
        for (MenuActions action : values()) {
            if (action.valeur.equals(choix)) {
                return action;
            }
        }
        return INVALIDE;
    }

    private static TitreEvenement demanderTitre(Scanner scanner) {
        System.out.print("Titre : ");
        return new TitreEvenement(scanner.nextLine());
    }

    private static DateEvenement demanderDate(Scanner scanner) {
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

    private static DureeEvenement demanderDuree(Scanner scanner) {
        System.out.print("Durée (minutes) : ");
        return new DureeEvenement(Integer.parseInt(scanner.nextLine()));
    }
}