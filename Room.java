/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Models a single room. This class contains fields for a text description of a room,
 * an image of the room, an inventory (so items can be placed in the room) and events 
 * that might transpire in that room.
 * @author Alan Brown, Alex White, Jared Moseley, Maxwell Twente
 */
public class Room {
    private String description;
    private String imagePath;
    private BufferedImage img;
    private Inventory inventory;
    private ArrayList<Event> events;
    
    /**
     * Constructor for a Room object
     * @param description String description of the room.
     * @param imagePath String file path for where the Room's image is stored
     */
    public Room(String description, String imagePath)
    {
        this.description = description;
        this.imagePath = imagePath;
		events = new ArrayList<Event>();
        loadImage();
    }
    
    /**
     * Loads an image located at imagePath
     */
    private void loadImage()
    {
        try {
            img = ImageIO.read(new File(imagePath));
            img = resizeImage(img, 508, 333);
        } catch (IOException ex) {
            System.out.println("Image not found!");
        }
    }
    /**
     * Resizes an image to make it more suitable for display.
     * @param image the image that is to be resized
     * @param width the desired width of the image
     * @param height the desired height of the image
     * @return a BufferedImage with the input width and height
     */
    
    private static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    /**
     * Gets the image that has been privately stored
     * @return the image of the room
     */
    public BufferedImage getImage()
    {
        return img;
    }
    
    /**
     * Gets the privately stored description of the room
     * @return the description of the room
     */
    public String getDescription()
    {
        return description;
    }
    //gets array list of events for the room
    //returns events
    public ArrayList<Event> getEvents()
    {
        return events;
    }
    //gets the @param eventNum then returns the eventsNum
    public Event getEvent(int eventNum)
    {
        return events.get(eventNum);
    }
    //adds an event to the current room @param e
    public void addEvent(Event e)
    {
        System.out.println(e);
        events.add(e);
    }
    //adds an item to the current room @param i
    public void addItem(Item i)
    {
        inventory.addItem(i);
    }
}
