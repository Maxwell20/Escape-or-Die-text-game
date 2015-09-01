/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

/**
 *
 * @author Jared660
 */
public class ItemEvent extends Event {
    
    String name;
    String descriptionItem;
    String imagePath;
    int roomNumber;
    int eventNumber;

    public ItemEvent(String description, String imagePath, String itemName, int roomNum, int eventNum, String itemDescription, boolean enabled) {
        super(description, enabled);
        this.imagePath = imagePath;
        descriptionItem = itemDescription;
        roomNumber = roomNum;
        eventNumber = eventNum;
        name = itemName;
    }
    //trigger function for adding the item to the players inventory at the time of event.
    public void trigger()
    {
        //add item to players inventory
        //I believe we could just give the player an inventory
       
        
        Inventory.addItem(new Item(name, descriptionItem, imagePath, roomNumber, eventNumber)); //creates an item and adds it to players inventory
        Game.displayText("\nYou pick up the "+name);
        enabled = false; //makes this event not enabled so it will disapear from dropdown list
        Game.updateItems(this.name);
        
    }
    
    //I think I agree with alan we need some kinda trigger. When these are selected in the drop down box the event needs to do something.
    
    
}
