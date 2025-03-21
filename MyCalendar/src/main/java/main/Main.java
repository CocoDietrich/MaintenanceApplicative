package main;

import actions.*;
import user.Utilisateur;
import user.UtilisateurList;
import utils.ConsoleUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUI.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI.afficherBanniereAccueil();
        UtilisateurList utilisateurs = new UtilisateurList();
        MenuActions menuActions = new MenuActions(scanner, utilisateurs);

        while (true) {
            Utilisateur utilisateurActuel = menuActions.demanderConnexion();
            CalendarManager calendar = new CalendarManager();
            Menu menu = new Menu(actions());

            boolean changerUtilisateur = false;

            while (!changerUtilisateur) {
                menu.afficher();
                saisie("Votre choix : ");
                int choix = lireChoix(scanner);

                Action action = menu.getAction(choix);
                action.executer(calendar, scanner);

                changerUtilisateur = action instanceof ChangerUtilisateurAction;
            }
        }
    }

    private static int lireChoix(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // traité dans getAction
        }
    }

    private static List<Action> actions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new VoirEvenementsAction());
        actions.add(new AjouterEvenementAction());
        actions.add(new VoirEvenementsPeriodeAction());
        actions.add(new VerifierConflitsAction());
        actions.add(new SupprimerEvenementAction());
        actions.add(new ChangerUtilisateurAction());
        actions.add(new QuitterAction());
        return actions;
    }
}
