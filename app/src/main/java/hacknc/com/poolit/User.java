package hacknc.com.poolit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Christopher on 10/29/2016.
 */

public class User {
    private List<User> friends;
    private String name;
    private List<Event> events;
    private int score;
    private String userID;
    private String accountID;

    public User(String name) {
        friends = new ArrayList<User>();
        this.name = name;
        events = new ArrayList<Event>();
        score = 0;
    }

    /**
     * Returns the list of friends associated with this user
     * @return The friends attached to this account
     */
    public List<User> getFriends() { return friends; }

    /**
     * Adds a new friend to this user given a name
     * @param friend The name of the account you want to add
     */
    public void addFriend(User friend) {
        friends.add(friend);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Event> getEvents() { return events; }
    public void addEvent(String name, String info, double target, Date date, User[] members) {
        Event e = new Event(name, info, target, this, date); // Fill in relevant info
        for (User u : members) {
            e.addPending(u);
        }
        events.add(e);

        // Push event to server, add event to server's copy of each member's copy of "events"
        Server.getInstance().addEvent(e);
    }

    public void incrementScore(int i) { score += i; }

    public String getID() { return userID; }

    public void sync() {
        // Find server's copy of this User, sync values
    }

    public String getAccountID(){
        return accountID;
    }
}
