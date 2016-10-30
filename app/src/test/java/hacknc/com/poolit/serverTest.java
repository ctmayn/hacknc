package hacknc.com.poolit;
import org.junit.*;

import static junit.framework.Assert.assertFalse;

/**
 * Created by Robby on 10/30/2016.
 */

public class serverTest {

    public void testGetInstance(){
        assertFalse(Server.getInstance() == null);
    }
}
