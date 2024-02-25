import com.example.controller.PackageManager;
import com.example.model.APackage;
import com.example.model.Card;
import com.example.model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PackagePurchaseTest {
    private PackageManager packageManager;
    private User user;

    @Before
    public void setUp() {
        packageManager = new PackageManager();
        user = new User("testUser", "testPassword");
        user.setCoin(20); // Kullanıcının başlangıç parası

        APackage testPackage = new APackage();
        testPackage.addCard(new Card("Card1", "Dragon", 50));
        testPackage.addCard(new Card("Card2", "Knight", 35));
        testPackage.addCard(new Card("Card3", "Elf", 25));
        testPackage.addCard(new Card("Card4", "Goblin", 15));
        testPackage.addCard(new Card("Card5", "Troll", 40));
        packageManager.addPackage(testPackage); // PackageManager'a paket ekleyin.
    }

    @Test
    public void testPackagePurchase() {
        int initialCoin = user.getCoin();
        String response = packageManager.buy_package(user);
        assertEquals("SUCCESSFUL", response); // Satın alma işleminin başarılı olduğunu kontrol edin.
        assertTrue(user.getCoin() < initialCoin); // Kullanıcının parasının azaldığını kontrol edin.
        assertFalse(user.getPackages().isEmpty()); // Kullanıcının en az bir pakete sahip olduğunu kontrol edin.
    }
}
