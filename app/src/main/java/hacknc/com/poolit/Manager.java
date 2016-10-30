package hacknc.com.poolit;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Created by Robby on 10/29/2016.
 */

public class Manager {
    private List<User> users;
    private User currentUser;

    public void login(String id, String pass) throws Exception {
        if(currentUser != null){
            throw new Exception("Already logged in.");
        }
        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
        currentUser = Server.getInstance().login(id, hashed);
    }

    public List<User> friends(){

        return currentUser.getFriends();

    }

    public void addUser(User u){
        for(User use: users){
            if(u == use){
                return;
            }
        }
        users.add(u);
    }



}
