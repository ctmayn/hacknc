package hacknc.com.poolit;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;


/**
 * Created by Justin Patzer on 10/29/2016. Provides interface to server data
 */
public class Server {

    /** The instance of the server interface */
    private static Server instance;
    private DynamoDB db;

    private Server() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("http://localhost:8000");

        db = new DynamoDB(client);


    }

    /**
     * Returns the instance of the server, and creates it if it doesn't exist
     * @return The instance of the server
     */
    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }

        return instance;
    }

    public User getUser(String userID) {
        GetItemSpec spec = new GetItemSpec()
                .withPrimaryKey("userId", userID);

        Table table = db.getTable("Users");

        Item outcome = table.getItem(spec);

        User u = new User((String) outcome.get("userId"), (List<User>) outcome.get("friends"), (List<Event>) outcome.get("events"), (int) outcome.get("score"), userID );

        return u;
    }

    public Event getEvent(String eventID) {
        GetItemSpec spec = new GetItemSpec()
                .withPrimaryKey("userId", eventID);

        Table table = db.getTable("Users");

        Item outcome = table.getItem(spec);

        Event e = new Event((String) outcome.get("userId") , (String) outcome.get("info"), (int) outcome.get("target"), (int) outcome.get("currentAmount"), (User) outcome.get("owner"),
                (List<User>) outcome.get("members"), (List<User>) outcome.get("pending"), (List<User>) outcome.get("contributors"), (List<Double>) outcome.get("contributions"),
                (Date) outcome.get("date"), eventID);

        return e;
    }

    /**
     * Returns all the accounts matching the given input string
     * @param name The name of the account to look up
     * @return The list of accounts which match the lookup phrase
     */
    public List<User> getAccounts(String name) {
        //returns a list of accounts that match the given name (or partial name)
        return null;
    }

    /**
     * Puts a new user on the server
     * @param user The user to add
     */
    public void addAccount(User user) {
        // Adds a new user to the server
    }

    /**
     * Returns the list of events relevant to the given user
     * @param user The user to find events for
     * @return The list of events this user has been invited to, or created
     */
    public List<Event> getEvents(User user) {
        // Returns the events this user has created or been invited to
        return null;
    }

    /**
     * Adds the given event to every member's event list
     * @param event The event to add to the server
     */
    public void addEvent(Event event) {
        // For each account in event.getMembers(), add event to their list of events
        // Do the same for event.getOwner()
    }
    public void RSVP(String eventID, String userID, boolean accept){
        //Server finds a user by an ID, and an event by an ID. It then updates the lists in the event.

    }
}
