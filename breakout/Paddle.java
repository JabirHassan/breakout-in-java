
package breakout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Paddle {
    
    private BufferedImage paddle;
    private int width = 100, height = 25;
    //paddle positionss
    private int posX = 350;
    private int posY = 525 ;
    private int position = 0; //direction of paddles's movement
    
    public Paddle(){
         try {
            paddle = ImageIO.read(getClass().getResource("/images/paddle.png"));
        } catch (IOException ex) {
           
        }
    }
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public void update(){
        
        posX += position;
        //stops paddle moving out of the frame boundaries
        if(posX <= 0) posX = 0;
        if((posX + 100) >= 800) posX  = 700;
        
    }
    public void draw(Graphics2D g){
        //draw the paddle on the screen
      g.drawImage(paddle, posX, posY,width, height,  null);
    }
    
    public void MouseMoved(int m){
        //moves the paddle
        posX= m;
    }
    
    public void keyPressed(int k){
        
    }
    
    public void keyReleased(int k){
        
    }
}
