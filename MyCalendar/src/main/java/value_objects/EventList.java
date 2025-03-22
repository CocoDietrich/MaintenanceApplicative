package value_objects;

import event_types.Event;

import java.util.ArrayList;
import java.util.List;

// Classe permettant de stocker une liste d'événements
public class EventList {

    // Liste des événements
    private final List<Event> events;

    // Constructeur
    public EventList() {
        this.events = new ArrayList<>();
    }

    // Ajoute un événement à la liste
    public void add(Event event) {
        events.add(event);
    }

    // Vérifie si un événement est dans une période donnée
    public EventList eventsInPeriod(DateEvenement start, DateEvenement end) {
        List<Event> eventsInPeriod = new ArrayList<>();
        for (Event e : events) {
            if (e.isInPeriod(start, end)) {
                eventsInPeriod.add(e);
            }
        }
        EventList periodEvents = new EventList();
        for (Event e : eventsInPeriod) {
            periodEvents.add(e);
        }
        return periodEvents;
    }

    // Affiche les événements
    public void displayEvents() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    // Méthode permettant de détecter les conflits entre les événements (chevauchement horaire)
    public boolean detectConflicts() {
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isInConflict(events.get(j))) {
                    System.out.println("Conflit entre : " + events.get(i).description() + " et " + events.get(j).description());
                    return true;
                }
            }
        }
        return false;
    }

    // Supprime un événement par son identifiant
    public void removeById(String id) {
        for (Event e : events) {
            if (e.getId().getId().equals(id)) {
                events.remove(e);
                return;
            }
        }
    }

    public int size() {
        return events.size();
    }
}
