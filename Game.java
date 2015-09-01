/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.awt.image.BufferedImage;

/**
 * Officiates the game. It is essentially the controller of model, view, control.
 * @author Alan Brown, Alex White, Jared Mosley, Maxwell Twente
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    static Map map;
    static GUI gui;
    static DataParser parser;
    
    public static void main(String[] args) {
        parser = new DataParser();
        map = new Map();
        gui = GUI.getGUI();
        gui.setSize(840, 500);
        gui.setLocation(400,200);
        gui.setResizable(false);
        gui.setVisible(true);
        refreshEvents();
        
        gui.displayPicture(map.getCurrentRoom().getImage());
        gui.displayDescription(map.getCurrentRoom().getDescription());
  
        System.out.println("Running.");
    }
    //This function allows player movement on the map
    //takes a chatacter for directional movement
    public static void moveDirection(char direction)
    {
        //checks for exits in each specified direction
        int roomNum = map.checkExit(direction);
        //-1 is used as and indicator for no exit
        //exicutes if the there is no room to move to in specified direction
        if(roomNum == -1)
        {
            gui.displayDescription("\nThere is no exit to the ");
            if(direction == 'N')
                gui.displayDescription("North!");
            else if(direction == 'S')
                gui.displayDescription("South!");
            else if(direction ==  'E')
                gui.displayDescription("East!");
            else if(direction == 'W')
                gui.displayDescription("West!");
        }
        //will executes if room is specified as locked from the current room
        else if(map.isLocked(roomNum))
        {
            gui.displayDescription("\nThat door is locked!");
        }
        //gets the description of the current room from the load file 
        //executes if there is a valid room to move to
        else
        {
            map.setCurrentRoom(roomNum);
            System.out.println("You are in room: "+roomNum);
            gui.clearDescription();
            gui.displayPicture(map.getCurrentRoom().getImage());
            gui.displayDescription(map.getCurrentRoom().getDescription());
            gui.refreshEvents(map.getCurrentRoom().getEvents());
        }
    }
    //refreshes the events in the current room
    public static void refreshEvents()
    {
        gui.refreshEvents(map.getCurrentRoom().getEvents());
    }
    //clears the text description of the current room
    public static void clearText()
    {
        gui.clearDescription();
        
    }
    //displays text description for the current room
    //takes a string as an arguemnt
    public static void displayText(String text)
    {
        gui.displayDescription(text);
    }
    //displays the room image for the gui 
    //takes an buffered image as an arguemnt
    public static void displayImage(BufferedImage img)
    {
        gui.displayPicture(img);
    }
    
    public static void updateItems(String itemName)
    {
        gui.updateItems(itemName);
    }
}
