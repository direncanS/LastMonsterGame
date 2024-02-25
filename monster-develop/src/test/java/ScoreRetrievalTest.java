import com.example.controller.ScoreBoardController;
import com.example.model.User;
import com.example.server.Response;
import com.example.enums.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreRetrievalTest {

    private ScoreBoardController scoreBoardController;
    private User user;

    @Before
    public void setup() {
        scoreBoardController = new ScoreBoardController();
        user = new User("testUser", "testPassword");

    }

    @Test
    public void testGetScoreBoards() {
        Response response = scoreBoardController.getScoreBoards(user);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());

    }
}
