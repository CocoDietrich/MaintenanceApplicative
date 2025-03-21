package main;

import actions.Action;
import utils.ConsoleUI;

import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUI.*;

public class Menu {
    private final List<Action> actions;

    public Menu(List<Action> actions) {
        this.actions = actions;
    }

    public void afficher() {
        titre("Menu principal");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(BLUE + " " + (i + 1) + " - " + actions.get(i).nom() + RESET);
        }
        separateur();
    }

    public Action getAction(int choix) {
        if (choix >= 1 && choix <= actions.size()) {
            return actions.get(choix - 1);
        }
        return new Action() {
            @Override
            public void executer(CalendarManager calendar, Scanner scanner) {
                erreur("Choix invalide.");
            }

            @Override
            public String nom() {
                return "Invalide";
            }
        };
    }
}
