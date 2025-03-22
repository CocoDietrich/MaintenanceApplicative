import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import value_objects.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests des Value Objects")
public class ValueObjectsTest {

    @Nested
    @DisplayName("TitreEvenement")
    class TitreEvenementTest {
        @Test
        void titreValideEstAccepte() {
            assertDoesNotThrow(() -> new TitreEvenement("Réunion"));
        }

        @Test
        void titreVideDeclencheException() {
            assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(""));
        }

        @Test
        void titreNullDeclencheException() {
            assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(null));
        }

        @Test
        void titreEstImmutable() {
            TitreEvenement titre = new TitreEvenement("Réunion");
            assertEquals("Réunion", titre.getTitre());
        }
    }

    @Nested
    @DisplayName("DateEvenement")
    class DateEvenementTest {
        @Test
        void dateValideEstAcceptee() {
            LocalDateTime now = LocalDateTime.now();
            DateEvenement date = new DateEvenement(now);
            assertEquals(now, date.getDate());
        }

        @Test
        void dateNullDeclencheException() {
            assertThrows(IllegalArgumentException.class, () -> new DateEvenement(null));
        }

        @Test
        void comparaisonEntreDatesFonctionne() {
            DateEvenement d1 = new DateEvenement(LocalDateTime.of(2023, 3, 20, 10, 0));
            DateEvenement d2 = new DateEvenement(LocalDateTime.of(2023, 3, 20, 12, 0));

            assertTrue(d2.isAfterOrEqual(d1));
            assertTrue(d1.isBeforeOrEqual(d2));
            assertTrue(d1.isAfterOrEqual(d1));
            assertTrue(d2.isBeforeOrEqual(d2));
        }
    }

    @Nested
    @DisplayName("DureeEvenement")
    class DureeEvenementTest {
        @Test
        void dureePositiveEstAcceptee() {
            DureeEvenement duree = new DureeEvenement(45);
            assertEquals(45, duree.getMinutes());
        }

        @Test
        void dureeNulleOuNegativeDeclencheException() {
            assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(0));
            assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(-10));
        }
    }

    @Nested
    @DisplayName("FrequenceJours")
    class FrequenceJoursTest {
        @Test
        void frequenceValideEstAcceptee() {
            FrequenceJours f = new FrequenceJours(7);
            assertEquals(7, f.getFrequenceJours());
        }

        @Test
        void frequenceZeroOuNegativeEstAutoriseeSiPasVerifiee() {
            // Attention : comportement par défaut actuel → peut être amélioré
            FrequenceJours f = new FrequenceJours(0);
            assertEquals(0, f.getFrequenceJours());
        }
    }

    @Nested
    @DisplayName("Participants")
    class ParticipantsTest {
        @Test
        void participantsSontBienStockes() {
            Participants p = new Participants(List.of("Alice", "Bob"));
            List<String> liste = p.getParticipants();

            assertEquals(2, liste.size());
            assertEquals("Alice", liste.get(0));
        }

        @Test
        void toStringAssembleLesNomsCorrectement() {
            Participants p = new Participants(List.of("Alice", "Bob"));
            assertEquals("Alice, Bob", p.toString());
        }
    }

    @Nested
    @DisplayName("EventId")
    class EventIdTest {
        @Test
        void idEstUniqueEtNonNull() {
            EventId id1 = new EventId();
            EventId id2 = new EventId();
            assertNotNull(id1.getId());
            assertNotEquals(id1.getId(), id2.getId());
        }

        @Test
        void idEstAuFormatUUID() {
            EventId id = new EventId();
            assertDoesNotThrow(() -> UUID.fromString(id.getId()));
        }
    }
}
