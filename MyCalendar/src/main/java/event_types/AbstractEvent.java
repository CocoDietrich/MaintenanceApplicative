package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.EventId;
import value_objects.TitreEvenement;

import java.time.LocalDateTime;

// Classe abstraite pour factoriser les événements
public abstract class AbstractEvent implements Event {
    protected final EventId id;
    public final TitreEvenement titre;
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

    @Override
    public boolean isInConflict(Event event) {
        if (event instanceof AbstractEvent) {
            AbstractEvent other = (AbstractEvent) event;
            boolean startsDuring = dateDebut.getDate().isBefore(other.getFin()) && dateDebut.getDate().isAfter(other.dateDebut.getDate());
            boolean startsAtSameTime = dateDebut.getDate().isEqual(other.dateDebut.getDate());
            boolean endsDuring = getFin().isAfter(other.dateDebut.getDate()) && getFin().isBefore(other.getFin());
            boolean endsAtSameTime = getFin().isEqual(other.getFin());
            boolean completelyOverlaps = dateDebut.getDate().isBefore(other.dateDebut.getDate()) && getFin().isAfter(other.getFin());
            return startsDuring || startsAtSameTime || endsDuring || endsAtSameTime || completelyOverlaps;
        }
        return false;
    }
}