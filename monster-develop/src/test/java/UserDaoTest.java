import com.example.dao.UserDAO;
import com.example.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
    UserDAO userDAO;
    @Before
    public void firstly(){
        this.userDAO=new UserDAO();
    }
    @Test
    public void dublicate_createUser(){
        User usr=new User("xx","xx");
        Assert.assertEquals("SUCCEED",userDAO.create(usr));
        Assert.assertNotEquals("SUCCEED",userDAO.create(usr));

    }
}
