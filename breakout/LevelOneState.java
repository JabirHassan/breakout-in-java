
package breakout;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class LevelOneState extends GameState{
    
    private ArrayList<BufferedImage> bricks; // list of bricks images
    private ArrayList<Rectangle> positions; //list of bricks positions
    private int brickWidth = 80;
    private int brickHeight = 60;
    private Brick brick = Brick.getInstance();
    private Paddle paddle;
    private Ball ball;
    private BufferedImage background;
    private boolean ballReleased = false;
    private boolean gameOver = false;
    private int score;
    private Font font;
    
    public LevelOneState(GameStateManager gsm){
        this.gsm = gsm;   
        bricks = new ArrayList<>();
        positions = new ArrayList<>();
        //get brick images and add to list
        bricks.add(brick.getBlue());
        bricks.add(brick.getBrown());
        bricks.add(brick.getDark_blue());
        bricks.add(brick.getGreen());
        bricks.add(brick.getPurple());
        bricks.add(brick.getRed());
        
        paddle = new Paddle();
        ball = new Ball();
    }
    //initialise the brick positions 
    public void brickPositions(){
        for(int i = 100; i < 280; i+=60){
            for(int j = 0; j < 800; j+=80){
                positions.add(new Rectangle(j, i, brickWidth, brickHeight));
            }
        }
    }

    @Override
    public void init() {
        
        gameOver = false;
        try {
            background = ImageIO.read(getClass().getResource("/images/background1.png"));
        } catch (IOException ex) {
            
        }    
        //reset the score  and 
        score = 0;
        //clear the positions list and initalise a new one
        positions.clear();
        brickPositions();

    }

    @Override
    public void update() {
        //update the paddle and ball positions
        paddle.update();
        ball.update(paddle.getPosX(), paddle.getPosY(), paddle.getWidth());
        //detect collision between ball and bricks
        for(Rectangle r : positions){
            
            if(r.x == ball.getPosX() + ball.getWidth() && r.y - 1  <= ball.getPosY()  && r.y + r.height + 1 >= ball.getPosY() + ball.getHeight()  ){
                r.height = 0; 
                r.width = 0;
                score++;
                ball.setxDir(-5);
            }
            
            if(r.x + r.width == ball.getPosX() && r.y - 1 <= ball.getPosY()  && r.y + r.height +1 >= ball.getPosY() + ball.getHeight()  ){
                r.height = 0;
                r.width = 0;
                score++;
                ball.setxDir(5);
            }
            if(r.y + r.height == ball.getPosY() && r.x - 1 <= ball.getPosX()  && r.x + r.width  + 1 >= ball.getPosX() + ball.getWidth() ){
                r.height = 0;
                r.width = 0;
                score++;
                ball.setyDir(5);
            }
            if(r.y  == ball.getPosY() + ball.getHeight() && r.x  - 1 <= ball.getPosX()  && r.x + r.width + 1 >= ball.getPosX() + ball.getWidth() ){
                r.height = 0;
                r.width = 0;
                score++;
                ball.setyDir(-5);                
            }
                        
        }
        //if all lives are lost
        if(ball.getLives() == 0){
            //reset the values
            ball.setLives(3);
            score = 0;
            gameOver = true;
            //change to  the GameOverState
            gsm.setState(GameStateManager.GAMEOVERSTATE);
        }
        //if all the bricks have been cleared
        if(score == positions.size()){
            //reset the ball position
            ball.setBallRelease(false);
            //reset the lives
            ball.setLives(3);
            positions.clear();
            gameOver = false; 
            //change to Win state
            gsm.setState(GameStateManager.WINSTATE);
        }
    }
      
    @Override
    public void draw(Graphics2D g) {
        //draw the background 
        g.drawImage(background, 0 , 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);       
        g.setFont(new Font("Century Gothis", Font.BOLD, 25));
        g.drawString("Score: "+score, 20, 30);
        g.drawString("Lives: "+ball.getLives(), 680, 30);
        if(gameOver == false){
            
            int index = 0;
            for(Rectangle r : positions){  
                g.drawImage(bricks.get(index), r.x, r.y, r.width, r.height, null);
                index++;
                if(index == bricks.size()) index = 0;
            }
        }
        
        paddle.draw(g);
        ball.draw(g);
    }
    
    @Override
    public void MouseMoved(int m){
        paddle.MouseMoved(m);
    }
    @Override
    public void mouseClicked(boolean me) {
        ball.mouseClicked(me);
    }

    @Override
    public void keyPressed(int k) {
        paddle.keyPressed(k);
        ball.keyPressed(k);

    }

    @Override
    public void keyReleased(int k) {
        paddle.keyReleased(0);
        ball.keyReleased(k);
    }
    
}
