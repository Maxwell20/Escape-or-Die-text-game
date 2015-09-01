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
import javax.imageio.ImageIO;

/**
 * This represents an item the player might find in the game.
 * @author ajw0026
 */
public class Item
{
    private String name;
    private String description;
    private BufferedImage icon;
    private String imagePath;
    private int roomNumber;
    private int eventNumber;
    // holds name discription and image path for the items
    // also keeps track of the specific event and room
    // for which the item may be found in
    //@param roomNumber @param eventNumber
    public Item(String name, String description, String imagePath, int roomNumber, int eventNumber)
    {
        this.name=name;
        this.description = description;
        this.imagePath = imagePath;
        this.roomNumber = roomNumber;
        this.eventNumber = eventNumber;
        loadImage();
    }
    
    /**
     * Loads an image located at imagePath
     */
    private void loadImage()
    {
        try 
        {
            icon = ImageIO.read(new File(imagePath));
            icon = resizeImage(icon, 93, 93);
        } 
            catch (IOException ex)
        {
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
    //returns the room number for the item
    public int getRoomNumber()
    {
        return roomNumber;
    }
    //returns the event number for the item
    public int getEventNumber()
    {
        return eventNumber;
    }

    /**
     * Gets the image that has been privately stored
     * @return the image of the room
     */
    public BufferedImage getImage()
    {
        return icon;
    }
    
    /**
     * Gets the privately stored description of the room
     * @return the description of the room
     */
    public String getDescription()
    {
        return description;
    }
    
    //returns name 
    public String getName()
    {
        return name;
    }
}