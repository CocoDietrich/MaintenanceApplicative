package value_objects;

// Value Object: DureeEvenement
public final class DureeEvenement {
    private final int minutes;

    public DureeEvenement(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive");
        }
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}