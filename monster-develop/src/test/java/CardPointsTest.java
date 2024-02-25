import com.example.model.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardPointsTest {


    @Test
    public void testCardPoints() {
        Card card = new Card("WaterWave", "Water", 40);
        assertEquals(40d,  card.getDamage(),0.6);
    }
}
