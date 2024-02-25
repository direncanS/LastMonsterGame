import com.example.model.APackage;
import com.example.model.Card;
import com.example.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAddPackageTest {

    @Test
    public void testUserAddPackage() {
        User user = new User("testUser", "testPassword");
        APackage aPackage = new APackage();
        aPackage.addCard(new Card("card1", "FireSpell", 20));
        user.addPackage(aPackage);
        assertFalse(user.getPackages().isEmpty());
    }
}
