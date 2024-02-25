import com.example.enums.HttpStatus;
import com.example.model.User;
import com.example.server.Response;
import com.example.trade.TradingDealController;
import org.junit.Test;
import static org.junit.Assert.*;

public class TradingDealCreationTest {

    @Test
    public void testCreateTradingDeal() {
        TradingDealController tradingDealController = new TradingDealController();
        String requestBody = "{\"Id\": \"1111\", \"CardToTrade\": \"card1\", \"Type\": \"Spell\", \"MinimumDamage\": 50}";
        User user = new User("testUser", "testPassword");
        Response response = tradingDealController.create(requestBody, user);
        assertEquals(HttpStatus.CREATED.getCode(), response.getStatusCode());
    }
}
