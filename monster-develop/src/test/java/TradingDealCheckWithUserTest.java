import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.server.Response;
import com.example.trade.TradingDealController;
import org.junit.Test;
import static org.junit.Assert.*;

public class TradingDealCheckWithUserTest {

    @Test
    public void testTradingDealCheckWithUser() {
        TradingDealController tradingDealController = new TradingDealController();
        User user = new User("testUser", "testPassword");
        Response response = tradingDealController.getUserTradingDeals(user);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }
}
