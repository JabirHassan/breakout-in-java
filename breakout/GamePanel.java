
package breakout;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class GamePanel extends Canvas implements Runnable, KeyListener, MouseMotionListener, MouseListener{
    
    public static int WIDTH = 800, HEIGHT = 600;
    
    private BufferedImage image;
    private Graphics2D g;
    
    private Thread thread;
    private boolean running = false;
    
    private GameStateManager gsm;
    private boolean mouseClicked = false;
    public GamePanel(){
        
        gsm = new GameStateManager();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        //initialise and start a thread
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            addMouseMotionListener(this);
            addMouseListener(this);
            thread.start();
        }
    }
    //initialise the game graphics
    public void init(){
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
    }

    @Override
    public void run() {
        init();
        while(running){
            update();
            draw();
            paint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public void update(){
        gsm.update();
    }
    
    
    public void draw(){
        gsm.draw(g);
    }
    //create and draw the buffered image
    public void paint(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g2 = bs.getDrawGraphics();
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
        bs.show();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        gsm.keyPressed(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        gsm.keyReleased(ke.getKeyCode());
    }

    public void mouseClicked(boolean me) {
        gsm.mouseClicked(mouseClicked);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        gsm.MouseMoved(me.getX());
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //track the mouse click
        if(me.getButton() == MouseEvent.BUTTON1)
            mouseClicked = true;
        gsm.mouseClicked(mouseClicked);
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
    
}
