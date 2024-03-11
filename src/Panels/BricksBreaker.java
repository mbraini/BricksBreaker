package Panels;

import Game.*;
import Game.GameLoop;
import Items.Brick;
import Items.OIG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BricksBreaker extends JPanel {
    public final static int GAME_WIDTH =Game.GAME_WIDTH;
    public final static int GAME_HEIGHT =Game.GAME_HEIGHT * 9 / 10;
    public static String username;
    public static String difficulty;
    public static Color ballColor;
    public static Color brickColor = Color.WHITE;
    public static Color backgroundColor;
    public static Color itemColor;
    public static int ballXVelocity;
    public static int ballYVelocity;
    public static int ballPower;
    public static int gravity = 1;
    public static int brickWidth = GAME_WIDTH / 6;
    public static int brickHeight = GAME_HEIGHT / 9;
    public static int currentBricksHP = 1;
    public static boolean isRunning;
    public static ArrayList<OIG> oigArrayList;
    public GameLoop gameLoop;

    public BricksBreaker(){
        this.setLayout(null);
        this.setBounds(0,Game.GAME_HEIGHT / 10, Game.GAME_WIDTH, Game.GAME_HEIGHT * 9 / 10);
        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0 ;i <oigArrayList.size() ;i++){
            oigArrayList.get(i).draw(g);
        }
    }

    public void start(){
        if (difficulty == null){
            difficulty = "Medium";
        }
        oigArrayList = new ArrayList<>();
        gameLoop = new GameLoop(this);
        isRunning = true;

        addBricks();
        gameLoop.start();
    }

    public void addBricks(){
        if (difficulty.equals("Easy")){
            ArrayList<Integer> randoms = GameHelper.GenerateRandomBrickLocation(2);
            for (int i =0 ;i < randoms.size() ;i++){
                oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 ,0 ,brickWidth ,brickHeight ,currentBricksHP));
            }
        }
        else if (difficulty.equals("Medium")){
            ArrayList<Integer> randoms = GameHelper.GenerateRandomBrickLocation(3);
            System.out.println(randoms.get(0));
            System.out.println(randoms.get(1));
            System.out.println(randoms.get(2));
            for (int i =0 ;i < randoms.size() ;i++){
                oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 ,0 ,brickWidth ,brickHeight ,currentBricksHP));
            }
        }
        else {
            ArrayList<Integer> randoms = GameHelper.GenerateRandomBrickLocation(4);
            for (int i =0 ;i < randoms.size() ;i++){
                oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 ,0 ,brickWidth ,brickHeight ,currentBricksHP));
            }
        }
    }
}
