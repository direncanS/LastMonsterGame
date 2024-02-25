import com.example.controller.Battle;
import com.example.enums.Type;
import com.example.model.Card;
import com.example.model.ScoreBoard;
import com.example.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayBattleRoundTest {

    @Test
    public void testPlayBattleRound() {
        Battle battle = new Battle();
        User player1 = new User("user1", "password1");
        User player2 = new User("user2", "password2");

        Card card1 = new Card("card1", "WaterSpell", 30);
        card1.setElement_type(Type.WATER);
        Card card2 = new Card("card2", "FireMonster", 40);
        card2.setElement_type(Type.FIRE);

        player1.getDeck().add_card_to_deck(card1);
        player2.getDeck().add_card_to_deck(card2);

        battle.start_battle(player1);
        battle.start_battle(player2);

        ScoreBoard scoreBoard = new ScoreBoard();
        battle.play_a_round(card1, card2, scoreBoard);

        assertTrue(scoreBoard.getPlayer1Damage() != scoreBoard.getPlayer2Damage());
    }
}
