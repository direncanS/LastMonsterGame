import com.example.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserCoinUpdateTest {

    private User user;

    @Before
    public void setup() {
        user = new User();
        user.setCoin(20); // Assuming initial coin balance is 20
    }

    @Test
    public void testCoinUpdateAfterPurchase() {
        // Assuming the cost of a package or operation is 5 coins
        int cost = 5;
        user.setCoin(user.getCoin() - cost);

        assertEquals(15, user.getCoin()); // Expecting the new coin balance to be 15
    }
}
