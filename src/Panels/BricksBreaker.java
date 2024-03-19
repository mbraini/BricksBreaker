package Panels;

import Game.*;
import Game.GameLoop;
import Items.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class BricksBreaker extends JPanel implements MouseMotionListener,MouseListener {
    public final static int GAME_WIDTH =Game.GAME_WIDTH;
    public final static int GAME_HEIGHT =Game.GAME_HEIGHT * 9 / 10;
    public final static int REFRESH_RATE = 10;
    public static String username;
    public static String difficulty;
    public static Color ballColor = Color.WHITE;
    public static Color brickColor = Color.WHITE;
    public static Color backgroundColor = Color.BLACK;
    public static Color itemColor;
    public static int ballVelocity = 6;
    public static int ballPower = 1;
    public static int ballCount = 1;
    public static int currentBallAimed = 0;
    public static int ballRadios = 10;
    public static int itemRadios = 10;
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
    public static Color ballItemColor = Color.GREEN;
    public static Color speedItemColor = Color.RED;
    public static Color powerItemColor = Color.BLUE;
    public GameLoop gameLoop;

    public BricksBreaker(){
        this.setLayout(null);
        this.setBounds(0,Game.GAME_HEIGHT / 10, Game.GAME_WIDTH, Game.GAME_HEIGHT * 9 / 10);
        this.setBackground(backgroundColor);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0 ;i <oigArrayList.size() ;i++){
            if (oigArrayList.get(i).isVisible) {
                oigArrayList.get(i).draw(g);
            }
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
        GamePanel.pt.start();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        addBricksAndItems();
        gameLoop.start();
    }

    public void addBricksAndItems(){
        int BrickCount;
        if (difficulty.equals("Easy")){
            BrickCount = 2;
        }
        else if (difficulty.equals("Medium")){
            BrickCount = 3;
        }
        else {
            BrickCount = 4;
        }
        ArrayList<Integer> randoms = GameHelper.GenerateRandomBrickLocation(BrickCount);
        for (int i =0 ;i < randoms.size() - 1 ;i++){
            oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 ,0 ,brickWidth ,brickHeight ,currentBricksHP ,null));
        }
        Random random = new Random();
        int itemPossibility = random.nextInt(300);
        Item item;
        if (itemPossibility < 280){
            item = new EarthquakeItem();
            oigArrayList.add(new Brick(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 ,0 ,brickWidth ,brickHeight ,currentBricksHP ,(SpecialItem) item));
            ((EarthquakeItem) item).setBrick((Brick) oigArrayList.get(oigArrayList.size() - 1));
            oigArrayList.add(item);
        }
        else {
            item = new SpeedItem(0, 0);
            oigArrayList.add(new SpeedItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
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
            int ballCountClone = ballCount;
            ballAimingTimer = new Timer(ballAimingDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (BricksBreaker.inTurn && BricksBreaker.currentBallAimed != ballCountClone){
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
                        BricksBreaker.currentBallAimed++;
                    }
                    else {
                        currentBallAimed = 0;
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

    public void pushBricks() {
        for (int i = 0; i < oigArrayList.size() ;i++){
            if (oigArrayList.get(i) instanceof Brick) {
                Brick brick = (Brick) oigArrayList.get(i);
                brick.setY(brick.getY() + brick.getHeight());
            }
        }
    }
}
