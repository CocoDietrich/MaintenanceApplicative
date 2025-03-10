package trivia;

public class Player {
    private final String name;
    private int position = 0;
    private int coins = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        if (position == 12) {
            return 1;
        } else {
            return position + 1;
        }
    }

    public int getCoins() {
        return coins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void move(int roll) {
        position = (position + roll) % 12;
        if (position == 0) position = 12; // Assurer que le joueur ne retombe pas sur 0
    }

    public void addCoin() {
        coins++;
    }

    public void goToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void getOutOfPenaltyBox() {
        inPenaltyBox = false;
    }
}
