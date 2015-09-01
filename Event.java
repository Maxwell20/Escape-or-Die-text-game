/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EscapeOrDie;

/**
 * This represents, in a broad sense, things that can happen.
 * This might include animations or jump scares. It could even include potential endings.
 * @author ajw0026
 */
public abstract class Event {
    String description;
    boolean enabled;
    
    public Event(String description, boolean enabled)
    {
        this.description = description;
        this.enabled = enabled;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void enable()
    {
        enabled = true;
    }
    
    public void disable()
    {
        enabled = false;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public String toString()
    {
        return description;
    }
    
    public abstract void trigger();
}
