import user.Utilisateur;
import user.UtilisateurList;
import value_objects.*;
import event_types.*;
import java.util.Scanner;

// A refactoriser encore (tout mettre dans MenuActions)
public class Main {
    public static void main(String[] args) {
        UtilisateurList utilisateurs = new UtilisateurList();
        Scanner scanner = new Scanner(System.in);
        Utilisateur utilisateurActuel = null;

        while (utilisateurActuel == null) {
            System.out.println("=== Connexion ===");
            System.out.println("1 - Se connecter");
            System.out.println("2 - Créer un compte");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();
            if (choix.equals("1")) {
                utilisateurActuel = utilisateurs.connexion(scanner);
            } else if (choix.equals("2")) {
                utilisateurActuel = utilisateurs.ajouterUtilisateur(scanner);
            }
        }

        CalendarManager calendar = new CalendarManager();
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
            System.out.println("8 - Changer d'utilisateur");
            System.out.println("9 - Quitter");
            System.out.print("Votre choix : ");

            MenuActions action = MenuActions.from(scanner.nextLine());
            action.executer(calendar, scanner);

            if (action == MenuActions.CHANGER_UTILISATEUR) {
                utilisateurActuel = null;
                while (utilisateurActuel == null) {
                    System.out.println("=== Connexion ===");
                    System.out.println("1 - Se connecter");
                    System.out.println("2 - Créer un compte");
                    System.out.print("Votre choix : ");

                    String choix = scanner.nextLine();
                    if (choix.equals("1")) {
                        utilisateurActuel = utilisateurs.connexion(scanner);
                    } else if (choix.equals("2")) {
                        utilisateurs.ajouterUtilisateur(scanner);
                    }
                }
            }
        }
        scanner.close();
    }
}
