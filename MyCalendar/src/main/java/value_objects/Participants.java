package value_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Participants {

    private final List<String> participants;

    public Participants(List<String> participants) {
        this.participants = new ArrayList<>(participants);
    }

    public static Participants collecter(Scanner scanner) {
        System.out.println("Entrez les participants (séparés par des virgules): ");
        String participants = scanner.nextLine();
        return new Participants(List.of(participants.split(", ")));
    }

    public List<String> getParticipants() {
        return participants;
    }

    public String toString() {
        return String.join(", ", participants);
    }
}
