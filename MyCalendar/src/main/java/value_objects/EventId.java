package value_objects;

import java.util.UUID;

// Value Object: EventId
public final class EventId {
    private final String id;

    public EventId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}