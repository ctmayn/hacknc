package hacknc.com.poolit;
import org.junit.*;

import java.util.Date;

import static junit.framework.Assert.*;

/**
 * Created by Robby on 10/30/2016.
 */


public class eventTest {

    @Test
    public void testEvent(){

        User user1 = new User("Bob");
        Date date1 = new Date();
        Event e1 = new Event("Barbeque","This is a barbeque.", 50, user1, date1);
        Event e2 = new Event("Prison Break","This is a prison break..", 50, user1, date1);
        assertTrue(e1 != null);
        assertEquals("Barbeque", e1.getTitle());
        assertEquals("This is a barbeque.", e1.getInfo());
        assertEquals(50.0, e1.getTarget());
        assertEquals(user1, e1.getOwner());
        assertEquals(date1, e1.getEventDate());


    }
    @Test

    public void testContribute(){
        User user1 = new User("Bob");
        User user2 = new User("Justin");
        User user3 = new User("Chris");

        Date date1 = new Date();
        Event e1 = new Event("Barbeque","This is a barbeque.", 150, user1, date1);
        e1.contribute(user2, 50);
        assertEquals(50.0,e1.getCurrent());
        assertEquals(user2, e1.getContributors().get(0));
        assertEquals(50.0, e1.getContributions().get(0));
        e1.contribute(user2,100);
        assertEquals(user2, e1.getContributors().get(1));
        assertEquals(100.0, e1.getContributions().get(0));



    }

}
