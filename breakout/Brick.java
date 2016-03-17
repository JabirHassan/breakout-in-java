
package breakout;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Brick {
    
    public static Brick instance = null;
    
    private BufferedImage red;
    private BufferedImage blue;
    private BufferedImage dark_blue;
    private BufferedImage brown;
    private BufferedImage green;
    private BufferedImage purple;
    
    private Brick(){
        try {
            //initialises all bricks
            red = ImageIO.read(getClass().getResource("/images/red.png"));
            blue = ImageIO.read(getClass().getResource("/images/blue.png"));
            dark_blue = ImageIO.read(getClass().getResource("/images/dark-blue.png"));
            brown = ImageIO.read(getClass().getResource("/images/brown.png"));
            purple = ImageIO.read(getClass().getResource("/images/purple.png"));
            green = ImageIO.read(getClass().getResource("/images/green.png"));
        } catch (IOException ex) {
            
        }
    }
    //return a singleton instance
    public static synchronized Brick getInstance(){
        if(instance == null)
            instance = new Brick();
        
        return instance;
    }

    public BufferedImage getRed() {
        return red;
    }

    public BufferedImage getBlue() {
        return blue;
    }

    public BufferedImage getDark_blue() {
        return dark_blue;
    }

    public BufferedImage getBrown() {
        return brown;
    }

    public BufferedImage getPurple() {
        return purple;
    }

    public BufferedImage getGreen() {
        return green;
    }
    
    
  
    
}
