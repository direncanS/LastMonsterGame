import com.example.controller.UserController;
import com.example.enums.HttpStatus;
import com.example.server.Response;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserRegistrationTest {

    @Test
    public void testUserRegistration() {
        UserController userController = new UserController();
        String requestBody = "{\"Username\": \"testUser\", \"Password\": \"testPassword\"}";
        Response response = userController.createUser(requestBody);
        assertEquals(HttpStatus.CREATED.getCode(), response.getStatusCode());
    }
}
