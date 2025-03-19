package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.EventId;
import value_objects.TitreEvenement;

import java.time.LocalDateTime;

// Classe abstraite pour factoriser les événements
public abstract class AbstractEvent implements Event {
    protected final EventId id;
    protected final TitreEvenement titre;
    public final DateEvenement dateDebut;
    protected final DureeEvenement duree;

    public AbstractEvent(TitreEvenement titre, DateEvenement dateDebut, DureeEvenement duree) {
        this.id = new EventId();
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    @Override
    public EventId getId() {
        return id;
    }

    protected LocalDateTime getFin() {
        return dateDebut.getDate().plusMinutes(duree.getMinutes());
    }

    @Override
    public boolean isInPeriod(DateEvenement start, DateEvenement end) {
        boolean isAfterOrEqual = dateDebut.isAfterOrEqual(start);
        boolean isBeforeOrEqual = getFin().isBefore(end.getDate()) || getFin().isEqual(end.getDate());
        return isAfterOrEqual && isBeforeOrEqual;
    }
}