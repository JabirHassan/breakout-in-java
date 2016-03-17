
package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GameOverState extends GameState{
    
    private BufferedImage background;
    private String[] choice = {"Play Again", "Main Menu"};
    private int currentChoice = 0;
    
    private Font font;
    
    public GameOverState(GameStateManager gsm){
        
        this.gsm = gsm;
        font = new Font("Impact", Font.PLAIN, 50);
        init();
    }

    @Override
    public void init() {
        try {
            background = ImageIO.read(getClass().getResource("/images/background1.png"));
        } catch (IOException ex) {
            
        }    
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(background, 0 , 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        g.setColor(Color.yellow);
        g.setFont(font);
        g.drawString("Game Over", 300, 280);
        g.setFont(new Font("Century Gothis", Font.PLAIN, 30));
        //displays the menu
        for(int i = 0; i < choice.length; i++){
            if(i == currentChoice)
                g.setColor(Color.blue);
            else
                g.setColor(Color.yellow);
            g.drawString(choice[i], 340, 350 + i * 40);
        }
    }
    public void select(){
        if(currentChoice == 0){
            gsm.setState(GameStateManager.LEVELONE);
        }
        if(currentChoice == 1)
            gsm.setState(GameStateManager.MENUSTATE);
    }
    
    @Override
    public void MouseMoved(int m) {

    }
    @Override
    
    public void mouseClicked(boolean me) {

    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER)
            select();
        if(k == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1)
                currentChoice = 0;
        }
        if(k == KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == choice.length)
                currentChoice = 0;
        }
    }

    @Override
    public void keyReleased(int k) {

    }
    
}
