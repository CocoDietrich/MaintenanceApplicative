import event_types.EvenementPeriodique;
import event_types.RdvPersonnel;
import event_types.Reunion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import value_objects.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests des classes event_types")
public class EventTypesTest {

    private TitreEvenement titre(String t) {
        return new TitreEvenement(t);
    }

    private DateEvenement date(int y, int m, int d, int h, int min) {
        return new DateEvenement(LocalDateTime.of(y, m, d, h, min));
    }

    private DureeEvenement duree(int minutes) {
        return new DureeEvenement(minutes);
    }

    @Nested
    @DisplayName("RdvPersonnel")
    class RdvPersonnelTest {
        @Test
        void creationEtDescriptionFonctionnent() {
            RdvPersonnel rdv = new RdvPersonnel(titre("Dentiste"), date(2024, 3, 25, 10, 0), duree(30));
            assertTrue(rdv.description().contains("Dentiste"));
            assertTrue(rdv.description().contains("RDV Personnel"));
        }

        @Test
        void estDansLaBonnePeriode() {
            RdvPersonnel rdv = new RdvPersonnel(titre("Test"), date(2024, 3, 25, 10, 0), duree(60));
            assertTrue(rdv.isInPeriod(date(2024, 3, 25, 9, 0), date(2024, 3, 25, 11, 0)));
            assertFalse(rdv.isInPeriod(date(2024, 3, 25, 11, 0), date(2024, 3, 25, 12, 0)));
        }
    }

    @Nested
    @DisplayName("EvenementPeriodique")
    class EvenementPeriodiqueTest {
        @Test
        void creationEtDescriptionFonctionnent() {
            EvenementPeriodique e = new EvenementPeriodique(
                    titre("Yoga"), date(2024, 3, 20, 8, 0), duree(45), new FrequenceJours(7));
            assertTrue(e.description().contains("tous les 7 jours"));
        }

        @Test
        void verifieBienLaPeriode() {
            EvenementPeriodique e = new EvenementPeriodique(
                    titre("Test"), date(2024, 3, 28, 14, 0), duree(30), new FrequenceJours(3));
            assertTrue(e.isInPeriod(date(2024, 3, 28, 13, 30), date(2024, 3, 28, 15, 0)));
        }
    }

    @Nested
    @DisplayName("Reunion")
    class ReunionTest {
        @Test
        void creationEtDescriptionFonctionnent() {
            Participants p = new Participants(List.of("Alice", "Bob"));
            Reunion r = new Reunion(titre("Projet"), date(2024, 3, 30, 9, 0), duree(90), "Salle A", p);
            String d = r.description();
            assertTrue(d.contains("Réunion"));
            assertTrue(d.contains("Alice"));
            assertTrue(d.contains("Salle A"));
        }

        @Test
        void verifieBienLaPeriode() {
            Participants p = new Participants(List.of("Alice"));
            Reunion r = new Reunion(titre("Stand-up"), date(2024, 3, 22, 10, 0), duree(15), "Salle B", p);
            assertTrue(r.isInPeriod(date(2024, 3, 22, 9, 45), date(2024, 3, 22, 10, 30)));
        }
    }

    @Nested
    @DisplayName("Détection de conflits entre événements")
    class ConflitTest {

        @Test
        void rdvEtReunionQuiChevauchentSontEnConflit() {
            RdvPersonnel rdv = new RdvPersonnel(titre("Médecin"), date(2024, 3, 25, 14, 0), duree(60));
            Reunion reunion = new Reunion(titre("Point projet"), date(2024, 3, 25, 14, 30), duree(60), "Salle B",
                    new Participants(List.of("Alice")));
            assertTrue(rdv.isInConflict(reunion));
            assertTrue(reunion.isInConflict(rdv));
        }

        @Test
        void deuxEvenementsSansChevauchementNeSontPasEnConflit() {
            RdvPersonnel e1 = new RdvPersonnel(titre("Pause"), date(2024, 3, 25, 8, 0), duree(30));
            RdvPersonnel e2 = new RdvPersonnel(titre("Réunion"), date(2024, 3, 25, 9, 0), duree(30));
            assertFalse(e1.isInConflict(e2));
        }

        @Test
        void evenementInclusDansUnAutreEstEnConflit() {
            RdvPersonnel grand = new RdvPersonnel(titre("Réunion longue"), date(2024, 3, 25, 10, 0), duree(120));
            RdvPersonnel petit = new RdvPersonnel(titre("Pause"), date(2024, 3, 25, 10, 30), duree(30));
            assertTrue(grand.isInConflict(petit));
            assertTrue(petit.isInConflict(grand));
        }
    }
}
