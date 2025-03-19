package event_types;

import value_objects.DateEvenement;
import value_objects.DureeEvenement;
import value_objects.FrequenceJours;
import value_objects.TitreEvenement;

public class EvenementPeriodique extends AbstractEvent {
    private final FrequenceJours frequenceJours;

    public EvenementPeriodique(TitreEvenement titre, DateEvenement dateDebut, DureeEvenement duree, FrequenceJours frequenceJours) {
        super(titre, dateDebut, duree);
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique n°" + this.getId().getId() + " : " + titre.getTitre() + " tous les " + frequenceJours.getFrequenceJours() + " jours à partir du " + dateDebut.getDate();
    }
}