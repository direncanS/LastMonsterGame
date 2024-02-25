import com.example.controller.Battle;
import com.example.model.Card;
import com.example.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardSelectionEvaluationTest {

    @Test
    public void testCardSelectionEvaluation() {
        Battle battle = new Battle();
        User user = new User("testUser", "testPassword");
        Card selectedCard = new Card("cardId", "FireDragon", 50);
        user.getDeck().add_card_to_deck(selectedCard);
        Card chosenCard = battle.selectRandomlyACard(user);
        assertNotNull(chosenCard);
        assertEquals("FireDragon", chosenCard.getName());
    }
}
