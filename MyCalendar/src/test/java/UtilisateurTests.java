import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import user.MotDePasseUtilisateur;
import user.NomUtilisateur;
import user.Utilisateur;
import user.UtilisateurList;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du package user")
public class UtilisateurTests {

    @Nested
    @DisplayName("NomUtilisateur & MotDePasseUtilisateur")
    class ValueObjectsTest {

        @Test
        void nomUtilisateurEstCorrectementStocke() {
            NomUtilisateur nom = new NomUtilisateur("mathieu");
            assertEquals("mathieu", nom.getNom());
        }

        @Test
        void motDePasseEstCorrectementStocke() {
            MotDePasseUtilisateur mdp = new MotDePasseUtilisateur("secret123");
            assertEquals("secret123", mdp.getMotDePasse());
        }
    }

    @Nested
    @DisplayName("Utilisateur")
    class UtilisateurTest {

        @Test
        void creationEtAccesNomMotDePasse() {
            Utilisateur u = new Utilisateur("alice", "mdp123");
            assertEquals("alice", u.getNom());
            assertEquals("mdp123", u.getMotDePasse());
        }
    }

    @Nested
    @DisplayName("UtilisateurList")
    class UtilisateurListTest {

        private UtilisateurList liste;

        @BeforeEach
        void setup() {
            liste = new UtilisateurList();
        }

        @Test
        void ajouterNouvelUtilisateurFonctionne() {
            Scanner scanner = createScannerInput("bob\npass123\n");
            Utilisateur u = liste.ajouterUtilisateur(scanner);
            assertNotNull(u);
            assertEquals("bob", u.getNom());
        }

        @Test
        void ajoutUtilisateurAvecNomDejaPrisRenvoieNull() {
            Scanner sc1 = createScannerInput("bob\npass123\n");
            Scanner sc2 = createScannerInput("bob\npass456\n");
            liste.ajouterUtilisateur(sc1);
            Utilisateur doublon = liste.ajouterUtilisateur(sc2);
            assertNull(doublon);
        }

        @Test
        void connexionReussieSiInfosCorrectes() {
            Scanner scAjout = createScannerInput("bob\nsecret\n");
            liste.ajouterUtilisateur(scAjout);

            Scanner scConnexion = createScannerInput("bob\nsecret\n");
            Utilisateur u = liste.connexion(scConnexion);
            assertNotNull(u);
            assertEquals("bob", u.getNom());
        }

        @Test
        void connexionEchoueAvecMauvaisMotDePasse() {
            Scanner scAjout = createScannerInput("bob\nsecret\n");
            liste.ajouterUtilisateur(scAjout);

            Scanner scConnexion = createScannerInput("bob\nmauvais\n");
            Utilisateur u = liste.connexion(scConnexion);
            assertNull(u);
        }

        @Test
        void estDejaUtiliseFonctionneCorrectement() {
            liste.ajouterUtilisateur(createScannerInput("bob\n123\n"));
            assertTrue(liste.estDejaUtilise("bob"));
            assertFalse(liste.estDejaUtilise("alice"));
        }

        private Scanner createScannerInput(String multilineInput) {
            return new Scanner(new ByteArrayInputStream(multilineInput.getBytes()));
        }
    }
}
