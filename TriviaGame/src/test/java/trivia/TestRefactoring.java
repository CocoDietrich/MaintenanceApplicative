package trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRefactoring {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testAddPlayer() {
        assertTrue(game.add("Alice"));
        assertTrue(game.add("Bob"));
        assertFalse(game.add("Alice")); // Duplicate name not allowed
    }

    @Test
    public void testMaxPlayers() {
        game.add("Alice");
        game.add("Bob");
        game.add("Charlie");
        game.add("David");
        game.add("Eve");
        game.add("Frank");
        assertFalse(game.add("Grace")); // Max players reached
    }

    @Test
    public void testGameStartRestriction() {
        game.roll(3);
        assertEquals(0, game.getPlayers().size()); // Le jeu ne doit pas démarrer sans joueurs
    }

    @Test
    public void testRollWithoutPenalty() {
        game.add("Alice");
        game.add("Bob");
        game.roll(4);
        assertEquals("Alice", game.getPlayers().get(game.getCurrentPlayerIndex()).getName());
    }

    @Test
    public void testWrongAnswerWithSecondChance() {
        game.add("Alice");
        game.add("Bob");
        game.roll(3);
        game.handleWrongAnswer();
        // Alice devrait avoir une seconde chance
        assertFalse(game.getPlayers().get(0).isInPenaltyBox());
        game.handleWrongAnswer(); // Deuxième erreur
        assertTrue(game.getPlayers().get(0).isInPenaltyBox());
    }

    @Test
    public void testCorrectAnswer() {
        game.add("Alice");
        game.add("Bob");
        game.roll(2);
        game.handleCorrectAnswer();
        assertEquals(1, game.getPlayers().get(0).getCoins());
    }

    @Test
    public void testWinningCondition() {
        game.add("Alice");
        game.add("Bob");
        for (int i = 0; i < 12; i++) {
            game.roll(2);
            game.handleCorrectAnswer();
        }
        assertTrue(game.didPlayerWin());
    }

    @Test
    public void testPenaltyBoxExit() {
        game.add("Alice");
        game.add("Bob");
        game.roll(3);
        game.handleWrongAnswer();
        game.roll(1);
        assertFalse(game.getPlayers().get(0).isInPenaltyBox());
    }

    @Test
    public void testPenaltyBoxStay() {
        game.add("Alice");
        game.add("Bob");
        game.roll(3);
        game.handleWrongAnswer();
        game.handleWrongAnswer();
        assertTrue(game.getPlayers().get(0).isInPenaltyBox());
    }
}
