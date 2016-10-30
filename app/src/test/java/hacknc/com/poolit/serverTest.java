package hacknc.com.poolit;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Robby on 10/30/2016.
 */

public class serverTest {

    @Test
    public void testGetInstance() {
        assertFalse(Server.getInstance() == null);
    }

    @Test
    public void testAddUser() {
        User u = new User("Bob");
        Server.getInstance().addUser(u);
        assertEquals(Server.getInstance().getUser(u.getID()), u);
    }

    @Test
    public void testAddEvent() {
        Event e = new Event("BBQ", "It's a party", 10.0, new User("Bob"), new Date());
        Server.getInstance().addEvent(e);
        assertEquals(Server.getInstance().getEvent(e.getID()), e);
    }

    @Test
    public void testGetUserByName() {
        User u1 = new User("Bob");
        User u2 = new User("Joe");
        User u3 = new User("Boe");

        Server.getInstance().addUser(u1);
        Server.getInstance().addUser(u2);
        Server.getInstance().addUser(u3);

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        assertEquals(Server.getInstance().getUsersByName("Bo"), users);
    }
}