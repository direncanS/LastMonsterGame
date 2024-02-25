import com.example.model.APackage;
import com.example.model.Card;
import com.example.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class CardRemovalFromPackageTest {

    private User user;
    private APackage aPackage;
    private Card cardToRemove;

    @Before
    public void setup() {
        user = new User("userTest", "password");
        aPackage = new APackage();
        cardToRemove = new Card("Card1", "Dragon", 10.0);

        aPackage.addCard(cardToRemove);
        user.addPackage(aPackage); // Adding the package to the user
    }

    @Test
    public void testCardRemovalFromPackage() {
        user.remove_a_card_from_package(cardToRemove);

        // Ensure the card is no longer in the package
        assertFalse(user.getPackages().get(0).getCards().contains(cardToRemove));
    }
}
