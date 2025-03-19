import value_objects.EventList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("=== Menu Gestionnaire d'Événements ===");
            System.out.println("1 - Voir les événements");
            System.out.println("2 - Ajouter un rendez-vous perso");
            System.out.println("3 - Ajouter une réunion");
            System.out.println("4 - Ajouter un évènement périodique");
            System.out.println("5 - Afficher les événements dans une période");
            System.out.println("6 - Vérifier les conflits");
            System.out.println("7 - Supprimer un événement");
            System.out.println("8 - Quitter");
            System.out.print("Votre choix : ");

            MenuActions action = MenuActions.from(scanner.nextLine());
            action.executer(calendar, scanner);
        }
        scanner.close();
    }
}