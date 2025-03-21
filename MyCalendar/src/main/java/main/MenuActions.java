package main;

import user.Utilisateur;
import user.UtilisateurList;

import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

import static utils.ConsoleUI.*;

public class MenuActions {

    private final Scanner scanner;
    private final UtilisateurList utilisateurs;

    public MenuActions(Scanner scanner, UtilisateurList utilisateurs) {
        this.scanner = scanner;
        this.utilisateurs = utilisateurs;
    }

    public Utilisateur demanderConnexion() {
        Map<String, Supplier<Utilisateur>> choixActions = new LinkedHashMap<>();
        choixActions.put("1", () -> utilisateurs.connexion(scanner));
        choixActions.put("2", () -> utilisateurs.ajouterUtilisateur(scanner));

        Utilisateur utilisateur = null;
        while (utilisateur == null) {
            titre("Connexion");
            System.out.println("1 - Se connecter");
            System.out.println("2 - Créer un compte");
            saisie("Votre choix : ");
            String choix = scanner.nextLine();
            utilisateur = choixActions.getOrDefault(choix, () -> null).get();
            if (utilisateur == null) {
                erreur("Nom d'utilisateur ou mot de passe incorrect.");
            }
        }
        succes("Connexion réussie !");
        return utilisateur;
    }
}
