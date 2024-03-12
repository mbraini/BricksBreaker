package Panels;

import Game.*;
import Game.GameLoop;
import Items.Ball;
import Items.Brick;
import Items.OIG;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BricksBreaker extends JPanel implements MouseMotionListener,MouseListener {
    public final static int GAME_WIDTH =Game.GAME_WIDTH;
    public final static int GAME_HEIGHT =Game.GAME_HEIGHT * 9 / 10;
    public final static int REFRESH_RATE = 10;
    public static String username;
    public static String difficulty;
    public static Color ballColor = Color.WHITE;
    public static Color brickColor = Color.WHITE;
    public static Color backgroundColor;
    public static Color itemColor;
    public static int ballVelocity = 5;
    public static int ballPower = 1;
    public static int ballCount = 1;
    public static int ballRadios = 10;
    public static int gravity = 1;
    public static int brickWidth = GAME_WIDTH / 6;
    public static int brickHeight = GAME_HEIGHT / 9;
    public static int currentBricksHP = 1;
    public static boolean isRunning;
    public static boolean inTurn = false;
    public static Point aimingFirstPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
    public static Point aimingSecondPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
    public static ArrayList<OIG> oigArrayList;
    public static Timer ballAimingTimer;
    public static final int ballAimingDelay = 70;
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
        if (!inTurn){
            paintAiming(g);
        }
    }

    public void start(){
        if (difficulty == null){
            difficulty = "Medium";
        }
        oigArrayList = new ArrayList<>();
        gameLoop = new GameLoop(this);
        isRunning = true;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

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

    public void paintAiming(Graphics g){
        g.setColor(Color.WHITE);
        g.drawLine(aimingFirstPoint.x ,aimingFirstPoint.y ,aimingSecondPoint.x ,aimingSecondPoint.y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!inTurn) {
            int x = e.getX();
            int y = e.getY();
            if (aimingFirstPoint.x == x) {
                return;
            }
            double m = ((aimingFirstPoint.y - y) / (double) (aimingFirstPoint.x - x));
            int xm, ym;
            xm = 0;
            ym = (int) (aimingFirstPoint.y - m * aimingFirstPoint.x);
            if (ym >= 0 && ym <= GAME_HEIGHT) {
                aimingSecondPoint = new Point(xm, ym);
                GameHelper.aimingCollision(xm, ym);
            }

            ym = 0;
            xm = (int) ((m * aimingFirstPoint.x - aimingFirstPoint.y) / m);
            if (xm >= 0 && xm <= GAME_WIDTH) {
                aimingSecondPoint = new Point(xm, ym);
                GameHelper.aimingCollision(xm, ym);
            }

            xm = GAME_WIDTH;
            ym = (int) (m * (xm - aimingFirstPoint.x) + aimingFirstPoint.y);
            if (ym >= 0 && ym <= GAME_HEIGHT) {
                aimingSecondPoint = new Point(xm, ym);
                GameHelper.aimingCollision(xm, ym);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!inTurn && aimingSecondPoint.x != aimingFirstPoint.x && aimingSecondPoint.y != aimingFirstPoint.y){
            inTurn = true;
            ballAimingTimer = new Timer(ballAimingDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (BricksBreaker.inTurn && BricksBreaker.ballCount != 0){
                        double x,y;
                        y = BricksBreaker.aimingSecondPoint.y - BricksBreaker.aimingFirstPoint.y;
                        x = BricksBreaker.aimingSecondPoint.x - BricksBreaker.aimingFirstPoint.x;
                        double m = x/y;
                        double yVelocity = Math.sqrt(Math.pow(BricksBreaker.ballVelocity ,2) / (Math.pow(m ,2) + 1));
                        double xVelocity = Math.sqrt(Math.pow(BricksBreaker.ballVelocity ,2) - Math.pow(yVelocity ,2));
                        BricksBreaker.oigArrayList.add(new Ball(
                                BricksBreaker.aimingFirstPoint.x,
                                BricksBreaker.aimingFirstPoint.y,
                                xVelocity * (x / Math.abs(x)) ,
                                yVelocity * (y / Math.abs(y)),
                                BricksBreaker.ballRadios
                        ));
                        BricksBreaker.ballCount--;
                    }
                    else {
                        BricksBreaker.ballAimingTimer.stop();
                    }
                }
            });

            ballAimingTimer.start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
