package breakout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {

    private BufferedImage b;
    private int width = 10, height = 10;
    private int posX = 387;
    private int posY = 500;
    private int xDir = -5;
    private int yDir = -5;
    private boolean ballRelease = false;
    private int lives = 3;

    public Ball() {
        try {
            b = ImageIO.read(getClass().getResource("/images/ball.png"));
        } catch (IOException ex) {

        }
    }

    public void update(int x, int y, int p_width) {
        //if the space bar is pressed
        if (ballRelease) {
            posX = posX + xDir;
            posY = posY + yDir;
        } else {
            //if space bar not presed, position the ball above the paddle
            posX = (x + (p_width / 2)) - 10;
            posY = y - 15;
        }
        //rebounds off left boundary
        if (posX <= 0) {
            xDir = 5;
        }
        ////rebounds off right boundary
        if (posX >= 785) {
            xDir = -5;
        }
        //rebounds off top boundary
        if (posY <= 0) {
            yDir = 5;
        }
        //rebounds off the paddle
        if ((posX + 10 >= x && (posX + 5) <= (x + p_width)) && (posY + 15) == y) {
            yDir = -5;
        }
        ////if paddle misses ball
        if (posY + 15 >= 600) {
            lives--;
            ballRelease = false;

        }
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setBallRelease(boolean ballRelease) {
        this.ballRelease = ballRelease;
    }

    public void draw(Graphics2D g) {
        g.drawImage(b, posX, posY, width, height, null);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public BufferedImage getB() {
        return b;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getxDir() {
        return xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void mouseClicked(boolean me) {
        if (me == true) {
            ballRelease = true;
        }
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
