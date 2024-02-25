import com.example.dao.UserDAO;
import com.example.model.Stats;
import com.example.model.User;
import com.example.server.Sessions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class EqualHashCodeTest {

    @Test
    public void testEqualsForStatsClass() throws JsonProcessingException {
        //stats not have overriden equals method
        Stats stats1=new Stats();
        Stats stats2=new Stats();
        Assert.assertEquals(false, stats1.equals(stats2));
    }

    @Test
    public void testEqualsForUserClass() throws JsonProcessingException {
        //stats not have overriden equals method
        User stats1=new User();
        User stats2=new User();
        Assert.assertEquals(true, stats1.equals(stats2));
    }

    @Test
    public void test2_EqualsForUserClassWithValues() throws JsonProcessingException {
        User originalUser = new User("koenc", "pass_test");
        User convertedUser = new User("koenc", "pass_test");
        Assert.assertEquals(true, convertedUser.equals(originalUser));
        Assert.assertEquals(originalUser.getUsername(), convertedUser.getUsername());
        Assert.assertEquals(originalUser.getPassword(), convertedUser.getPassword());
    }

}