package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.TitreEvenement;


public class Anniversaire extends AbstractEvent {

    public Anniversaire(TitreEvenement titre, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, dateDebut, duree);
    }

    @Override
    public String description() {
        return "Anniversaire n°" + this.getId().getId() + " : " + titre.getTitre() + " le " + dateDebut.getDate().toString().split("T")[0];
    }
}
