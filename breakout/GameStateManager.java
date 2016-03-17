
package breakout;

//this class tracks and updates the different game states 

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {
    
    public ArrayList<GameState> gameStates;
    public static int currentState;
    
    public static int MENUSTATE = 0;
    public static int GAMEOVERSTATE = 1;
    public static int LEVELONE = 2;
    public static int WINSTATE = 3;
    
    public GameStateManager(){
        
        gameStates = new ArrayList<>();
        currentState = MENUSTATE;
        
        gameStates.add(new MenuState(this));
        gameStates.add(new GameOverState(this));
        gameStates.add(new LevelOneState(this));
        gameStates.add(new WinState(this));
    }
    //set the game state
    public void setState(int state){
        currentState = state;
        gameStates.get(state).init();
    }
   //update the game state
    public void update(){
        gameStates.get(currentState).update();
    }
    //draw the state's graphics
    public void draw(Graphics2D g){
        gameStates.get(currentState).draw(g);
    }
    //track the mouse movement
    public void MouseMoved(int m) {
       gameStates.get(currentState).MouseMoved(m);
    }
    //initalise the mouseClicked boolean
    public void mouseClicked(boolean me) {
        gameStates.get(currentState).mouseClicked(me);
    }
    //track the keyboard events
    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }
    
    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
    
}
