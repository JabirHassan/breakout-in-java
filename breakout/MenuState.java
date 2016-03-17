
package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class MenuState extends GameState{
    
    private BufferedImage background;
    
    private String[] choice = {"Play", "Quit"};
    private int currentChoice = 0;
    
    private Font font;
    
    public MenuState(GameStateManager gsm){
        this.gsm = gsm;
        font = new Font("Century Gothic", Font.BOLD, 70);
        init();
    }

    @Override
    public void init() {
        
        try {
            background = ImageIO.read(getClass().getResource("/images/background.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
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
        g.drawString("Breakout", 280, 280);
        g.setFont(new Font("Century Gothis", Font.PLAIN, 35));
        //displays the menu
        for(int i = 0; i < choice.length; i++){
            if(i == currentChoice)
                g.setColor(Color.blue);
            else
                g.setColor(Color.yellow);
            g.drawString(choice[i], 400, 350 + i * 40);
        }

    }
    //tracks user selection
    public void select(){
        if(currentChoice == 0)
            gsm.setState(GameStateManager.LEVELONE);
        if(currentChoice == 1)
            System.exit(0);
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
