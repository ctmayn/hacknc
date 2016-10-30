package hacknc.com.poolit;

import com.amazonaws.services.dynamodbv2.document.Item;

import java.util.*;
/**
 * Created by Christopher on 10/29/2016.
 */



public class Event {
    /** The name of the event. */
    private String title;
    /** The info for the event. */
    private String info;
    /** The target amount of dollars for the event. */
    private double target;
    /**  The current amount of dollars for the event. */
    private double currentAmount;
    /** The owner of the event. */
    private User owner;
    /** A list of members who are attending the event. */
    private List<User> members;
    /** A list of members who have been invited. */
    private List<User> pending;
    /** A list of members who have contributed money to the event. */
    private List<User> contributors;
    /** A list of the contributions that have been made to the event. */
    private List<Double> contributions;
    /** The date of the event. */
    private Date eventDate
    /** The ID of the event. */
    private String eventID;

    public Event(String tit, String info, double target, User owner, Date date){
        this.setTitle(tit);
        this.setInfo(info);
        this.setTarget(target);
        this.setOwner(owner);
        this.setEventDate(date);
        currentAmount = 0;
        members = new ArrayList<User>();
        pending = new ArrayList<User>();
        contributors = new ArrayList<User>();
        contributions = new ArrayList<Double>();
    }

    public Event(Item i) {
        title = (String) i.get("userId");
        info = (String) i.get("info");
        target = (int) i.get("target");
        currentAmount = (int) i.get("currentAmount");
        owner = (User) i.get("owner");
        members = (List<User>) i.get("members");
        pending = (List<User>) i.get("pending");
        contributors = (List<User>) i.get("contributors");
        contributions = (List<Double>) i.get("contributions");
        eventDate = (Date) i.get("date");
        eventID = (String) i.get("userId");
    }
    /**
     * Returns the target funding.
     * @return The goal in dollars.
     */
    public double getTarget(){ return target; }

    /**
     * Sets target to a new value
     * @param tar The new value for target.
     */
    private void setTarget(double tar){ target = tar; }

    /**
     * Returns the current amount of money contributed
     * @return currentAmount The current amount of money in the pot.
     */
    public double getCurrent(){
        return currentAmount;
    }

    /**
     * Allows a user to contribute a given amount to the Event.
     * @param account The contributing User
     * @param amount The amount to be contributed
     */
    public void contribute(User account, int amount){
        //Pull money from bank from a user.
        Transfer.transfer(account, owner, amount);
        addContributor(account, amount);
    }

    /**
     * Confirms a member is attending
     * @param acc The member to be added to the list of members.
     */
    private void addMember(User acc){
        members.add(acc);
    }

    /**
     * Returns a list of confirmed attending members.
     * @return A list of members who are a part of the event.
     */
    public List<User> getMembers(){
        return members;
    }

    /**
     * Adds a member to the contributors list, as well as an amount contributed
     * @param account The contributor to be added to the list of contributors.
     */
    private void addContributor(User account, double amount){
        for (int i = 0; i < contributors.size(); i++) {
            if (contributors.get(i).equals(account)) {
                contributions.set(i, contributions.get(i) + amount);
                return;
            }
        }
        contributors.add(account);
        contributions.add(amount);
    }

    /**
     * Returns those who have contributed
     * @return A list of contributors who have given to the event.
     */
    public List<User> getContributors(){
        return contributors;
    }

    /**
     * Returns a list of contributions, index matched to contributors
     * @return A list of contributions to the event.
     */
    public List<Double> getContributions() { return contributions; }

    /**
     * Returns the title of the Event
     * @return The title of the event.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Sets the title of the event
     * @param title The title that of the event.
     */
    public void setTitle(String title){
        this.title = title;
    }


    /**
     * Gets the info of the event.
     * @return The info for the event.
     */
    public String getInfo(){
        return info;
    }

    /**
     * Sets the info for the event.
     * @param inf The info to be set.
     */
    public void setInfo(String inf){
        info = inf;
    }

    /**
     * Allows a user to RSVP to the event
     * @param acc The user in question.
     * @param response Whether they confirmed or denied attendance.
     */
    public void acceptdeny(User acc, boolean response){
        Server.getInstance().RSVP(this.getID(), acc.getID(), response);
        pending.remove(acc);
        if(response == true) {
            addMember(acc);
        }
    }

    /**
     * Invites a user to the event.
     * @param acc Adds the user to the pending list.
     */
    public void addPending(User acc) {
        pending.add(acc);
    }

    /**
     * Sets the owner of the event
     * @param acc Sets the user to be the owner.
     */
    private void setOwner(User acc){
        owner = acc;
    }

    /**
     * Returns the user in charge of the event.
     * @return Returns the owner of the event.
     */
    public User getOwner(){
        return owner;
    }

    /**
     * Returns the unique ID for this event
     * @return Returns the eventID.
     */
    public String getID(){
        return eventID;
    }

    /**
     * Returns the date the event takes place
     * @return The date of the event.
     */
    public Date getEventDate(){
        return eventDate;
    }

    /**
     * Sets the date the event takes place
     * @param date The date of the event to be set.
     */
    public void setEventDate(Date date){
        eventDate = date;
    }


}
