package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.TitreEvenement;

// Implémentation des types d'événements
public class RdvPersonnel extends AbstractEvent {
    public RdvPersonnel(TitreEvenement titre, DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV Personnel n°" + this.getId().getId() + " : " + titre.getTitre() + " le " + dateDebut.getDate();
    }
}