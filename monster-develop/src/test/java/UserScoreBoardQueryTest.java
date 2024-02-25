import com.example.controller.ScoreBoardController;
import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.server.Response;
import com.example.server.Sessions;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserScoreBoardQueryTest {

    @Test
    public void testUserScoreBoardQuery() {
        ScoreBoardController scoreBoardController = new ScoreBoardController();
        User user = new User("testUser", "testPassword");
        Sessions.addUserToSession(user);
        Response response = scoreBoardController.getScoreBoards(user);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }
}
