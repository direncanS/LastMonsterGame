import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.example.controller.Battle;
import com.example.model.User;
import com.example.server.Response;

public class BattleTest {

    private Battle battle;
    private User user;

    @Before
    public void setUp() {
        battle = new Battle();
        user = new User("testUser", "testPassword");
    }

    @Test
    public void testStartBattle() {
        Response response = battle.start_battle(user);
        assertNotNull(response);
    }

}
