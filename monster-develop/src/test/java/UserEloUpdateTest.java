import com.example.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserEloUpdateTest {

    @Test
    public void testUserEloUpdate() {
        User user = new User("testUser", "testPassword");
        int initialELO = user.getCurrentELO();
        user.updateCurrentELO(5);
        assertEquals(initialELO + 5, user.getCurrentELO());
    }
}
