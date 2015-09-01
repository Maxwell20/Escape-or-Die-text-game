/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

/**
 *This is a kind of event that will display a message to the screen
 * @author Alan Brown
 */
public class MessageEvent extends Event{
    String description;
    String msg; // The message that will be output when the event is triggered
    boolean enable;
    Map map;
    GUI gui;
    //checks to see if the event is enabled 
    public MessageEvent(String description, String message, boolean enabled) {
        super(description, enabled);
        msg = message;
    }
    //trigger function for events
    //displays the message to the user
    public void trigger()
    {
        gui = GUI.getGUI();
        gui.displayDescription("\n");
        gui.displayDescription(msg);
    }
}
