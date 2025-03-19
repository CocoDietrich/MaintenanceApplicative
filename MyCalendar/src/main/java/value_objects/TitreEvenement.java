package value_objects;

// Value Object: TitreEvenement
public final class TitreEvenement {
    private final String titre;

    public TitreEvenement(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }
}