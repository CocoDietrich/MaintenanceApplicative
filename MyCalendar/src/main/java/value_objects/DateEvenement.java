package value_objects;

import java.time.LocalDateTime;

// Value Object: DateEvenement
public final class DateEvenement {
    private final LocalDateTime date;

    public DateEvenement(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isAfterOrEqual(DateEvenement start) {
        return date.isAfter(start.date) || date.isEqual(start.date);
    }

    public boolean isBeforeOrEqual(DateEvenement end) {
        return date.isBefore(end.date) || date.isEqual(end.date);
    }
}