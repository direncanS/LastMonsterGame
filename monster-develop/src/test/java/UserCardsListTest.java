import com.example.controller.UserController;
import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.model.Card;
import com.example.model.APackage;
import com.example.server.Response;
import com.example.server.Sessions;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserCardsListTest {

    private UserController userController;
    private User user;

    @Before
    public void setUp() {
        userController = new UserController();
        user = new User("testUser", "testPassword");

        // Test kullanıcısına kartlar ekleyin
        APackage aPackage = new APackage(); // APackage sınıfınıza uygun bir şekilde oluşturun
        aPackage.addCard(new Card("Card1", "Fire", 30));
        aPackage.addCard(new Card("Card2", "Water", 40));
        user.addPackage(aPackage);

        // Kullanıcıyı oturum listesine ekleyin
        Sessions.addUserToSession(user);
    }

    @Test
    public void testUserCardsList() {
        String token = "testUser"; // Gerçek uygulamada, bu token gerçek bir yetkilendirme mekanizması tarafından sağlanır
        Response response = userController.getUserCards(token);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }
}
