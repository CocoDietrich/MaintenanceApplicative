package event_types;

import value_objects.DateEvenement;
import value_objects.EventId;

// Interface pour les événements
public interface Event {
    EventId getId();
    String description();
    boolean isInPeriod(DateEvenement start, DateEvenement end);
    boolean isInConflict(Event event);
}