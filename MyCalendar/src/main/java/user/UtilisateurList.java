package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtilisateurList {

    private final List<Utilisateur> utilisateurs = new ArrayList<>();

    public Utilisateur connexion(Scanner scanner) {
        System.out.print("Nom d'utilisateur : ");
        String nom = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().equals(nom) && utilisateur.getMotDePasse().equals(motDePasse)) {
                System.out.println("Connexion réussie !");
                return utilisateur;
            }
        }

        System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
        return null;
    }

    public Utilisateur ajouterUtilisateur(Scanner scanner) {
        System.out.print("Nom d'utilisateur : ");
        String nom = scanner.nextLine();
        if (estDejaUtilise(nom)) {
            System.out.println("Ce nom d'utilisateur est déjà utilisé.");
            return null;
        }

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        Utilisateur utilisateur = new Utilisateur(nom, motDePasse);
        utilisateurs.add(utilisateur);
        System.out.println("Utilisateur créé !");
        return utilisateur;
    }

    public boolean estDejaUtilise(String nom) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }
}
