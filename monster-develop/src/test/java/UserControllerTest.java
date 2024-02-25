import org.junit.Assert;
import org.junit.Test;
import com.example.controller.UserController;
import com.example.model.User;
import com.example.server.Response;

public class UserControllerTest {

    @Test
    public void createUserTest() {
        UserController userController = new UserController();
        User newUser = new User("testUsername", "testPassword");
        Response response = userController.createUser(String.valueOf(newUser));
        Assert.assertEquals(201, response.getStatusCode()); // CREATED status code
    }
}
