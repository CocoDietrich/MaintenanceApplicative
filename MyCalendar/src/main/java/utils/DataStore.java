package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import event_types.Event;
import value_objects.EventList;
import user.UtilisateurList;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Essai de sérialisation/désérialisation avec Jackson (abandonné)
public class DataStore {

    private static final String USERS_PATH = "data/utilisateurs.json";
    private static final String EVENTS_PATH = "data/evenements.json";

    private final ObjectMapper mapper;

    public DataStore() {
        this.mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addAbstractTypeMapping(Event.class, Event.class);
        mapper.registerSubtypes(
                event_types.RdvPersonnel.class,
                event_types.Reunion.class,
                event_types.EvenementPeriodique.class,
                event_types.Anniversaire.class
        );
        mapper.registerModule(module);
    }

    public void sauvegarderUtilisateurs(UtilisateurList utilisateurs) {
        try {
            mapper.writeValue(new File(USERS_PATH), utilisateurs);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    public UtilisateurList chargerUtilisateurs() {
        try {
            return mapper.readValue(new File(USERS_PATH), UtilisateurList.class);
        } catch (IOException e) {
            System.out.println("Aucun fichier d'utilisateurs trouvé, initialisation d’une liste vide.");
            return new UtilisateurList();
        }
    }

    public void sauvegarderEvenements(EventList evenements) {
        try {
            mapper.writeValue(new File(EVENTS_PATH), evenements);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des événements : " + e.getMessage());
        }
    }

    public EventList chargerEvenements() {
        try {
            return mapper.readValue(new File(EVENTS_PATH), EventList.class);
        } catch (IOException e) {
            System.out.println("Aucun fichier d'événements trouvé, initialisation d’une liste vide.");
            return new EventList();
        }
    }
}
