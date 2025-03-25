import event_types.Anniversaire;
import event_types.RdvPersonnel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import value_objects.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour la classe Anniversaire")
public class AnniversaireTest {

    @Test
    void creationFonctionnelleAvecTitreEtDate() {
        TitreEvenement titre = new TitreEvenement("Anniversaire de Léo");
        DateEvenement date = new DateEvenement(LocalDateTime.of(2024, 5, 3, 0, 0));
        DureeEvenement duree = new DureeEvenement(1440); // 1 jour

        Anniversaire anniv = new Anniversaire(titre, date, duree);

        assertEquals(titre.getTitre(), anniv.description().split(":")[1].trim().split(" ")[0]);
        assertEquals(date.getDate(), anniv.dateDebut.getDate());
    }

    @Test
    void descriptionContientLeMotAnniversaireEtLaDate() {
        Anniversaire anniv = new Anniversaire(
                new TitreEvenement("Anniversaire de Jade"),
                new DateEvenement(LocalDateTime.of(2024, 12, 20, 0, 0)),
                new DureeEvenement(1440)
        );

        String desc = anniv.description();
        assertTrue(desc.contains("Anniversaire"));
        assertTrue(desc.contains("Jade"));
        assertTrue(desc.contains("2024"));
    }

    @Test
    void conflitEstDetecteAvecUnAutreEvenementLeMêmeJour() {
        Anniversaire anniv = new Anniversaire(
                new TitreEvenement("Anniversaire"),
                new DateEvenement(LocalDateTime.of(2024, 6, 10, 0, 0)),
                new DureeEvenement(1440)
        );
        RdvPersonnel rdv = new RdvPersonnel(
                new TitreEvenement("Réunion"),
                new DateEvenement(LocalDateTime.of(2024, 6, 10, 10, 0)),
                new DureeEvenement(60)
        );

        assertTrue(anniv.isInConflict(rdv));
        assertTrue(rdv.isInConflict(anniv));
    }

    @Test
    void periodeIncluantLaDateDoitContenirLAnniversaire() {
        Anniversaire anniv = new Anniversaire(
                new TitreEvenement("Anniversaire de Emma"),
                new DateEvenement(LocalDateTime.of(2024, 8, 15, 0, 0)),
                new DureeEvenement(1440)
        );

        DateEvenement debut = new DateEvenement(LocalDateTime.of(2024, 8, 14, 0, 0));
        DateEvenement fin = new DateEvenement(LocalDateTime.of(2024, 8, 16, 0, 0));

        assertTrue(anniv.isInPeriod(debut, fin));
    }
}
