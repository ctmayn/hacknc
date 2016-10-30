package hacknc.com.poolit;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Created by Robby on 10/29/2016.
 */

public class Manager {
    /**
     * The user who is currently logged in.
     */
    private User currentUser;

    /**
     * Logs the current user in to the program.
     * @param id The id of the user to be logged in.
     * @param pass The password of the user to be logged in.
     * @throws Exception
     */
    public void login(String id, String pass) throws Exception {
        if(currentUser != null){
            throw new Exception("Already logged in.");
        }
        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
        currentUser = Server.getInstance().login(id, hashed);
    }

    /**
     * Logs the current user out of the application.
     */
    public void logout(){
        currentUser = null;
    }

    /**
     * Returns a list of friends of the user.
     * @return The list of friends.
     */
    public List<User> friends(){

        return currentUser.getFriends();

    }

    /**
     * Finds a user given the user's name.
     * @param name The name of the user to be found.
     * @return The list of users found.
     */
    public List<User> findUser(String name){
        return Server.getInstance().getUsersByName(name);
    }

    /**
     *
     * @param friend The user to be added.
     */
    public void addFriend(User friend){
        currentUser.addFriend(friend);
    }

    /**
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event){
        Server.getInstance().addEvent(event);
    }

    /**
     *
     * @param friend The user that will be removed.
     */
    public void removeFriend(User friend){
        currentUser.removeFriend(friend);
    }

    /**
     *
     * @return The events the user is a part of.
     */
    public List<Event> userEvents(){
        return  currentUser.getEvents();
    }

    /**
     *
     * @param user The user to be added.
     */
    public void addUser(User user){
        Server.getInstance().addUser(user);
    }
    public void createUser(String name) {
        User user = new User(name);
        this.addUser(user);
    }


}
