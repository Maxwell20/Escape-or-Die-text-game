/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.util.ArrayList;

/**
 * This is a collection of item objects.
 * @author ajw0026
 */
public final class Inventory {
    private static ArrayList<Item> inventory = new ArrayList<>();
   
    private Inventory()
    {
        
    }
    //adds an item to the players inventory
    //takes an argument of type item
    //uses inventory
    //@param item 
    public static void addItem(Item item)
    {
        inventory.add(item);
        if(!(item.getRoomNumber() == -1 || item.getEventNumber() == -1))
        {
            Map.getRoom(item.getRoomNumber()).getEvent(item.getEventNumber()).enable();
        }
    }
    
    /**
     * Returns an item with the given item name
     * @param itemName
     * @return 
     */
    public static Item getItem(String itemName)
    {
        Item temp = null;
        for (Item inventory1 : inventory) {
            if (inventory1.getName().equals(itemName)) {
                temp = inventory1;
            }
        }
        return temp;
    }
    
    //removes item from the players inventory
    //calles the .remove function then gets the item
    //takes itemName as arguement
    public static void removeItem(String itemName)
    {
        inventory.remove(getItem(itemName));
    }
    
    //checks to see if player has a specific item in inventory
    //returns found if true
    public static boolean hasItem(String name)
    {
        boolean found = false;
        for (Item inventory1 : inventory)
        {
            if(inventory1.getName().equals(name)) found = true;
        }
        
        return found;
    }
}
