package Panels;
import Game.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static String username;
    public static String difficulty;
    public static Color ballColor;
    public static Color brickColor;
    public static Color backgroundColor;
    public static Color itemColor;
    public static int ballXVelocity;
    public static int ballYVelocity;
    public static int ballPower;
    public static int brickWidth;
    public static int brickHeight;


    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }
}
