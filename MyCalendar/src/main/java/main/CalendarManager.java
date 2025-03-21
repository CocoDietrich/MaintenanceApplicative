package main;

import event_types.Event;
import value_objects.DateEvenement;
import value_objects.EventList;

// Gestionnaire de calendrier
public class CalendarManager {
    public final EventList events;

    public CalendarManager() {
        this.events = new EventList();
    }

    public void ajouterEvent(Event event) {
        events.add(event);
    }

    public EventList eventsDansPeriode(DateEvenement debut, DateEvenement fin) {
        return events.eventsInPeriod(debut, fin);
    }

    public void afficherEvenements() {
        events.displayEvents();
    }
}