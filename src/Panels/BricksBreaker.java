package Panels;

import Game.Game;
import Game.GameLoop;
import Items.OIG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BricksBreaker extends JPanel {
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
    public static int currentBricksHP;
    public static boolean isRunning;
    public static ArrayList<OIG> oigArrayList;
    public GameLoop gameLoop;

    public BricksBreaker(){
        this.setLayout(null);
        this.setBounds(0,Game.GAME_HEIGHT / 10, Game.GAME_WIDTH, Game.GAME_HEIGHT * 9 / 10);
        this.setBackground(Color.RED);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0 ;i <oigArrayList.size() ;i++){
            oigArrayList.get(i).draw(g);
        }
    }

    public void start(){
        oigArrayList = new ArrayList<>();
        gameLoop = new GameLoop(this);
        isRunning = true;


        gameLoop.start();
    }

    public void addBricks(){
        if (difficulty.equals("Easy")){

        }
        else if (difficulty.equals("Medium")){

        }
        else {

        }
    }
}
