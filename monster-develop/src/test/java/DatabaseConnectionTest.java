import com.example.util.DBConnection;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class DatabaseConnectionTest {

    @Test
    public void ConnectionTest(){
        DBConnection connection=new DBConnection();
        assertNotEquals(null, connection.conn);
    }


}
