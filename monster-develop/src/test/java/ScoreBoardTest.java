import com.example.model.ScoreBoard;
import com.example.model.User;
import com.example.model.Card;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private User player1, player2;
    private Card player1Card, player2Card;

    @Before
    public void setup() {
        scoreBoard = new ScoreBoard();
        player1 = new User("player1", "password1");
        player2 = new User("player2", "password2");
        player1Card = new Card("card1", "Dragon", 10.0);
        player2Card = new Card("card2", "Knight", 8.0);
    }

    @Test
    public void testUpdateScoreBoard() {
        // Simulate a battle scenario
        scoreBoard.setPlayer1(player1);
        scoreBoard.setPlayer2(player2);
        scoreBoard.setPlayer1Cards(player1Card);
        scoreBoard.setPlayer2Cards(player2Card);

        scoreBoard.setWinner(player1);

        assertEquals(player1, scoreBoard.getWinner());
    }
}
