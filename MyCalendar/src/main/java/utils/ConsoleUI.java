// ConsoleUI.java
package utils;

public class ConsoleUI {
    // Couleurs ANSI
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BOLD = "\u001B[1m";

    public static void afficherBanniereAccueil() {
        String[] lignes = {
                "  ____      _                _            ",
                " / ___|__ _| | ___ _ __   __| | __ _ _ __ ",
                "| |   / _` | |/ _ \\ '_ \\ / _` |/ _` | '__|",
                "| |__| (_| | |  __/ | | | (_| | (_| | |   ",
                " \\____\\__,_|_|\\___|_| |_|\\__,_|\\__,_|_|   ",
                "|  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ",
                "| |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|",
                "| |  | | (_| | | | | (_| | (_| |  __/ |   ",
                "|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   ",
                "                          |___/           "
        };

        int width = 90; // largeur approximative du terminal
        System.out.println(BOLD + YELLOW);
        for (String ligne : lignes) {
            int padding = (width - ligne.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + ligne);
        }
        System.out.println(RESET);
    }

    public static void titre(String text) {
        System.out.println(BOLD + CYAN + "\n╔══════════════════════════════╗");
        System.out.println("║ " + text.toUpperCase());
        System.out.println("╚══════════════════════════════╝" + RESET);
    }

    public static void section(String text) {
        System.out.println(BLUE + "\n→ " + text + RESET);
    }

    public static void succes(String text) {
        System.out.println(GREEN + "✔ " + text + RESET);
    }

    public static void erreur(String text) {
        System.out.println(RED + "✖ " + text + RESET);
    }

    public static void saisie(String label) {
        System.out.print(YELLOW + label + RESET);
    }

    public static void separateur() {
        System.out.println(MAGENTA + "----------------------------------------" + RESET);
    }
}
