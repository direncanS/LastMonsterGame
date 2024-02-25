import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.example.dao.ScoreBoardDAO;
import com.example.model.Card;
import com.example.model.ScoreBoard;
import com.example.model.User;

public class ScoreBoardDaoTest {
    private ScoreBoardDAO scoreBoardDAO;
    @Before
    public void setUp() {
        scoreBoardDAO = new ScoreBoardDAO();
    }
    @Test
    public void testCreateScoreBoard() {
        User player1 = new User("player1", "password1");
        User player2 = new User("player2", "password2");
        Card card1 = new Card("cardId1", "FireGoblin", 40);

        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setPlayer1(player1);
        scoreBoard.setPlayer2(player2);
        scoreBoard.setPlayer1Cards(card1);

        String result = scoreBoardDAO.create(scoreBoard);
        assertEquals("SUCCEED", result);
    }

}
