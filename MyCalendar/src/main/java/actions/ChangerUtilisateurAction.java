// ChangerUtilisateurAction.java
package actions;

import main.CalendarManager;

import java.util.Scanner;

public class ChangerUtilisateurAction implements Action {

    @Override
    public void executer(CalendarManager calendar, Scanner scanner) {
        System.out.println("Déconnexion... Vous allez revenir au menu principal.");
    }

    @Override
    public String nom() {
        return "Changer d'utilisateur";
    }
}
