package trivia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game implements IGame {
   private static final int MAX_PLAYERS = 6;
   private final List<Player> players = new ArrayList<>();
   private final Set<String> playerNames = new HashSet<>();
   private final QuestionDeck questionDeck = new QuestionDeck();
   private int currentPlayerIndex = 0;
   private boolean gameStarted = false;

   public boolean add(String playerName) {
      if (gameStarted) {
         System.out.println("Cannot add new players after game has started.");
         return false;
      }
      if (players.size() >= MAX_PLAYERS) {
         System.out.println("Maximum number of players reached.");
         return false;
      }
      if (!playerNames.add(playerName)) {
         System.out.println("Player name must be unique.");
         return false;
      }
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      if (players.size() < 2) {
         System.out.println("At least two players are required to start the game.");
         return;
      }
      gameStarted = true;
      Player currentPlayer = players.get(currentPlayerIndex);

      System.out.println(currentPlayer.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) { // Seul un nombre impair peut sortir de la prison
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            currentPlayer.getOutOfPenaltyBox();
            currentPlayer.move(roll);
            System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPosition());
            askQuestion();
         } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
         }
      } else {
         currentPlayer.move(roll);
         System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPosition());
         askQuestion();
      }
   }

   private void askQuestion() {
      Player currentPlayer = players.get(currentPlayerIndex);
      String category = questionDeck.getCategoryForPosition(currentPlayer.getPosition() - 1);
      System.out.println("The category is " + category);
      System.out.println(questionDeck.drawQuestion(category));
   }

   public boolean handleCorrectAnswer() {
      Player currentPlayer = players.get(currentPlayerIndex);
      if (!currentPlayer.isInPenaltyBox()) {
         System.out.println("Answer was correct!!!!");
         currentPlayer.addCoin();
         System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getCoins() + " Gold Coins.");
         boolean winner = currentPlayer.getCoins() < 6;
         nextPlayer();
         return winner;
      } else {
         nextPlayer();
         if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
         }
         return true;
      }
   }

   public boolean wrongAnswer() {
      Player currentPlayer = players.get(currentPlayerIndex);
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer.getName() + " was sent to the penalty box");
      currentPlayer.goToPenaltyBox();
      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayerIndex++;
      if (currentPlayerIndex >= players.size()) {
         currentPlayerIndex = 0;
      }
   }
}
