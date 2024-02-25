import com.example.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class ObjMapperTest {

    @Test
    public void testObjMapper() throws JsonProcessingException {
        ObjectMapper objMapper=new ObjectMapper();
        User usr=new User("koenc","pass_test");
        String strJSON= objMapper.writeValueAsString(usr);

        User usrDondu=objMapper.readValue(strJSON,User.class);
        Assert.assertEquals(usr,usrDondu);
    }

}
