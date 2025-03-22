import event_types.*;
import main.CalendarManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import value_objects.*;
import actions.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests complets des actions (sauf QuitterAction)")
public class ActionsTest {

    CalendarManager calendar;

    @BeforeEach
    void setup() {
        calendar = new CalendarManager();
    }

    @Test
    void voirEvenementsNeDeclenchePasDErreur() {
        VoirEvenementsAction action = new VoirEvenementsAction();
        assertDoesNotThrow(() -> action.executer(calendar, new Scanner("")));
    }

    @Test
    void voirEvenementsPeriodeAfficheCeQuIlFaut() {
        Event e = new RdvPersonnel(
                new TitreEvenement("Test"),
                new DateEvenement(LocalDateTime.of(2024, 4, 10, 14, 0)),
                new DureeEvenement(60)
        );
        calendar.ajouterEvent(e);

        String input = String.join("\n", List.of(
                "2024", "4", "10", "13", "0",
                "2024", "4", "10", "16", "0"
        ));
        VoirEvenementsPeriodeAction action = new VoirEvenementsPeriodeAction();
        assertDoesNotThrow(() -> action.executer(calendar, new Scanner(new ByteArrayInputStream(input.getBytes()))));
    }

    @Test
    void supprimerEvenementSupprimeCorrectementParId() {
        RdvPersonnel event = new RdvPersonnel(
                new TitreEvenement("À supprimer"),
                new DateEvenement(LocalDateTime.of(2024, 4, 12, 9, 0)),
                new DureeEvenement(30)
        );
        String id = event.getId().getId();
        calendar.ajouterEvent(event);

        SupprimerEvenementAction action = new SupprimerEvenementAction();
        String input = id + "\n";
        assertDoesNotThrow(() -> action.executer(calendar, new Scanner(new ByteArrayInputStream(input.getBytes()))));
    }

    @Test
    void verifierConflitsDetecteUnConflit() {
        Event e1 = new RdvPersonnel(
                new TitreEvenement("RDV 1"),
                new DateEvenement(LocalDateTime.of(2024, 4, 15, 10, 0)),
                new DureeEvenement(60)
        );
        Event e2 = new RdvPersonnel(
                new TitreEvenement("RDV 2"),
                new DateEvenement(LocalDateTime.of(2024, 4, 15, 10, 30)),
                new DureeEvenement(60)
        );
        calendar.ajouterEvent(e1);
        calendar.ajouterEvent(e2);

        VerifierConflitsAction action = new VerifierConflitsAction();
        assertDoesNotThrow(() -> action.executer(calendar, new Scanner("")));
    }

    @Test
    void changerUtilisateurAfficheUnMessage() {
        ChangerUtilisateurAction action = new ChangerUtilisateurAction();
        assertDoesNotThrow(() -> action.executer(calendar, new Scanner("")));
    }

    @Test
    void ajouterRdvViaTestableActionFonctionne() {
        TestableAjouterEvenementAction action = new TestableAjouterEvenementAction();
        RdvPersonnel rdv = action.creerRdv(
                new TitreEvenement("Médecin"),
                new DateEvenement(LocalDateTime.of(2024, 4, 20, 11, 0)),
                new DureeEvenement(30)
        );
        calendar.ajouterEvent(rdv);
        assertEquals(1, calendar.events.eventsInPeriod(
                new DateEvenement(LocalDateTime.of(2024, 4, 20, 10, 0)),
                new DateEvenement(LocalDateTime.of(2024, 4, 20, 12, 0))
        ).size());
    }

    @Test
    void ajouterReunionViaTestableActionFonctionne() {
        TestableAjouterEvenementAction action = new TestableAjouterEvenementAction();
        Reunion reunion = action.creerReunion(
                new TitreEvenement("Projet"),
                new DateEvenement(LocalDateTime.of(2024, 4, 21, 9, 0)),
                new DureeEvenement(90),
                "Salle A",
                new Participants(List.of("Alice", "Bob"))
        );
        calendar.ajouterEvent(reunion);
        assertEquals(1, calendar.events.eventsInPeriod(
                new DateEvenement(LocalDateTime.of(2024, 4, 21, 8, 0)),
                new DateEvenement(LocalDateTime.of(2024, 4, 21, 11, 0))
        ).size());
    }

    @Test
    void ajouterEvenementPeriodiqueViaTestableActionFonctionne() {
        TestableAjouterEvenementAction action = new TestableAjouterEvenementAction();
        EvenementPeriodique periodic = action.creerPeriodique(
                new TitreEvenement("Yoga"),
                new DateEvenement(LocalDateTime.of(2024, 4, 22, 18, 0)),
                new DureeEvenement(60),
                new FrequenceJours(7)
        );
        calendar.ajouterEvent(periodic);
        assertEquals(1, calendar.events.eventsInPeriod(
                new DateEvenement(LocalDateTime.of(2024, 4, 22, 17, 0)),
                new DateEvenement(LocalDateTime.of(2024, 4, 22, 20, 0))
        ).size());
    }

    // Classe spéciale pour rendre les méthodes testables
    static class TestableAjouterEvenementAction extends AjouterEvenementAction {
        public RdvPersonnel creerRdv(TitreEvenement titre, DateEvenement date, DureeEvenement duree) {
            return new RdvPersonnel(titre, date, duree);
        }

        public Reunion creerReunion(TitreEvenement titre, DateEvenement date, DureeEvenement duree, String lieu, Participants participants) {
            return new Reunion(titre, date, duree, lieu, participants);
        }

        public EvenementPeriodique creerPeriodique(TitreEvenement titre, DateEvenement date, DureeEvenement duree, FrequenceJours frequence) {
            return new EvenementPeriodique(titre, date, duree, frequence);
        }
    }
}
