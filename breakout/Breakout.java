
package breakout;

import javax.swing.JFrame;


public class Breakout extends JFrame{

    
    public static void main(String[] args) {
        //set up the game frame
        JFrame frame = new JFrame("Breakoout");
        GamePanel panel = new GamePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
}
