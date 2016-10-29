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
    private Date eventDate;
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

    public Event(String title, String info, int target, int currentAmount, User owner, List<User> members, List<User> pending, List<User> contributors, List<Double> contributions, Date date, String eventID) {
        this.title = title;
        this.info = info;
        this.target = target;
        this.currentAmount = currentAmount;
        this.owner = owner;
        this.members = members;
        this.pending = pending;
        this.contributors = contributors;
        this.contributions = contributions;
        this.eventDate = date;
        this.eventID = eventID;
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
     *
     * @return Returns the target number of dollars for the event.
     */
    public double getTarget(){
        return target;

    }

    /**
     *
     * @param tar Sets the target number of dollars for the event.
     */
    public void setTarget(double tar){
        target = tar;

    }

    /**
     *
     * @return currentAmount The current amount of money in the pot.
     */
    public double getCurrent(){
        return currentAmount;
    }

    /**
     *
     * @param cur The amount to be
     */
    public void contribute(User acc, double cur){
        //Pull money from bank from a user.
    }

    /**
     *
     * @param acc The member to be added to the list of members.
     */
    public void addMember(User acc){
        members.add(acc);
    }

    /**
     *
     * @return A list of members who are a part of the event.
     */
    public List<User> getMembers(){
        return members;
    }

    /**
     *
     * @param acc The contributor to be added to the list of contributors.
     */
    public void addContributor(User acc){
        contributors.add(acc);
    }

    /**
     *
     * @return A list of contributors who have given to the event.
     */
    public List<User> getContributors(){
        return contributors;
    }

    /**
     *
     * @return Returns the title of the event.
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @param tit The title that of the event.
     */
    public void setTitle(String tit){
        title = tit;
    }
    public String getInfo(){
        return info;
    }
    public void setInfo(String inf){
        info = inf;
    }
    public void acceptdeny(User acc, boolean response){
        Server.getInstance().RSVP(this.getID(), acc.getID(), response);
        pending.remove(acc);
        if(response == true) {
            addMember(acc);
        }
    }

    /**
     *
     * @param acc Adds the user to the pending list.
     */
    public void addPending(User acc) {
        pending.add(acc);
    }

    /**
     *
     * @param acc Sets the user to be the owner.
     */
    public void setOwner(User acc){
        owner = acc;
    }

    /**
     *
     * @return Returns the owner of the event.
     */
    public User getOwner(){
        return owner;
    }

    /**
     *
     * @return Returns the eventID.
     */
    public String getID(){
        return eventID;
    }

    /**
     *
     * @return The date of the event.
     */
    public Date getEventDate(){
        return eventDate;
    }

    /**
     *
     * @param date The date of the event to be set.
     */
    public void setEventDate(Date date){
        eventDate = date;
    }


}
