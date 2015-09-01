/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

import java.awt.Dialog;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ajw0026
 */
public class EndingEvent extends Event{
    String fileName;
    String message;
    BufferedImage icon;
    
    public EndingEvent(String description, String fileName, String message, boolean enabled)
    {
        super(description, enabled);
        this.fileName = fileName;
        this.message = message;
        loadImage();
    }
    
    /**
     * Loads an image located at imagePath
     */
    private void loadImage()
    {
        try 
        {
            icon = ImageIO.read(new File(fileName));
            //icon = resizeImage(icon, 1178, 747);
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
    
    public static BufferedImage resizeImage(BufferedImage image, int width, int height)
    {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    public BufferedImage getImage()
    {
        return icon;
    }
    
    public void setImage(BufferedImage b)
    {
        icon = b;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public void trigger()
    {
        EndingDialog dialog;
        try {
            dialog = new EndingDialog(GUI.getGUI(), this, Dialog.ModalityType.APPLICATION_MODAL);
        } catch (InterruptedException ex) {
            Logger.getLogger(EndingEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
