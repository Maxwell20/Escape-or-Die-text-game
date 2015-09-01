
package EscapeOrDie;

/**
 * This is a special kind of event that will unlock a locked door.
 * @author Alan Brown
 */
public class UnlockEvent extends EscapeOrDie.Event{
    String description;
    boolean enable;
    int roomOne;
    int roomTwo;
    /**
    * Constructor for an Unlock Event. It doesn't matter which room is the second room
    * and which is the first room, as long as the even is meant to unlock the pathway between
    * the two rooms. 
    * @param description The words that will appear in the drop-down list
    * @param enabled Whether the event is enabled or not (true/false)
    * @param firstRoom The first room of the two rooms a path should be unlocked between
    * @param secondRoom The second room
    */
    public UnlockEvent(String description, boolean enabled, int firstRoom, int secondRoom) 
    {
        super(description, enabled);
        roomOne = firstRoom;
        roomTwo = secondRoom;
    }
    /**
     * This is the function that actually does the unlocking
     * by using information stored in this event.
     * All Events should have a "trigger" function that activates the Event.
     */
    @Override
    public void trigger()
    {
        Map.unlockRoom(roomOne, roomTwo);
        enabled = false;
    }
}