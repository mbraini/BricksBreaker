package Panels;
import Game.*;
import Items.OIG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    public static boolean isRunning;
    public static ArrayList<OIG> oigArrayList;
    public GameLoop gameLoop;


    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        isRunning = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        for (int i = 0 ;i <oigArrayList.size() ;i++){
//            oigArrayList.get(i).draw(g);
//        }
    }

    public void start(){
        gameLoop = new GameLoop(this);
        gameLoop.start();
    }
}
