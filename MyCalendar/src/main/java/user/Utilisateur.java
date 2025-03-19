package user;

public class Utilisateur {

    private final NomUtilisateur nom;
    private final MotDePasseUtilisateur motDePasse;

    public Utilisateur(String nom, String motDePasse) {
        this.nom = new NomUtilisateur(nom);
        this.motDePasse = new MotDePasseUtilisateur(motDePasse);
    }

    public String getNom() {
        return nom.getNom();
    }

    public String getMotDePasse() {
        return motDePasse.getMotDePasse();
    }
}
