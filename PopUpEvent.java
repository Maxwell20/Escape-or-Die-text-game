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
 *
 * @author ajw0026
 */
public class PopUpEvent extends Event{
    String imageName;
    private BufferedImage icon;
    
    public PopUpEvent(String description, boolean enabled, String imageName) {
        super(description, enabled);
        this.imageName = imageName;
        loadImage();
    }
    
    /**
     * Loads an image located at imagePath
     */
    private void loadImage()
    {
        try 
        {
            icon = ImageIO.read(new File(imageName));
            icon = resizeImage(icon, 508, 333);
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
    //trigger for popupevent
    @Override
    public void trigger() {
        Game.displayImage(icon);
    }
    
}
