package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.Participants;
import value_objects.TitreEvenement;

import java.util.ArrayList;
import java.util.List;

public class Reunion extends AbstractEvent {
    private final String lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, DateEvenement dateDebut, DureeEvenement duree, String lieu, Participants participants) {
        super(titre, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion n°" + this.getId().getId() + " : " + titre.getTitre() + " au lieu " + lieu + " avec " + String.join(", ", participants.getParticipants()) + " le " + dateDebut.getDate();
    }
}