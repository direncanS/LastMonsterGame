import com.example.controller.UserController;
import com.example.enums.HttpStatus;
import com.example.server.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserLoginTest {

    private UserController userController;

    @Before
    public void setup() {
        userController = new UserController();
    }

    @Test
    public void testUserLogin() {
        String requestBody = "{\"Username\": \"kienboec\", \"Password\": \"daniel\"}";
        Response response = userController.isHave(requestBody);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }
}
