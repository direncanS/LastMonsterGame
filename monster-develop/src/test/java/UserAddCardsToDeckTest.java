import com.example.model.Card;
import com.example.model.Deck;
import com.example.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAddCardsToDeckTest {

    @Test
    public void testUserAddCardsToDeck() {
        User user = new User("testUser", "testPassword");
        Deck deck = new Deck();
        Card card1 = new Card("card1", "WaterSpell", 30);
        Card card2 = new Card("card2", "FireMonster", 40);
        deck.add_card_to_deck(card1);
        deck.add_card_to_deck(card2);
        assertEquals(2, deck.getMonsterCards().size() + deck.getSpellCards().size());
    }
}
