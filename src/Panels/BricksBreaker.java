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
    public static final int brickInitialX = 50;
    public static final int brickInitialY = 40;
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
    public static boolean newAim = false;
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
            oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 + brickInitialX ,brickInitialY ,brickWidth ,brickHeight ,currentBricksHP ,null));
        }
        Random random = new Random();
        int itemPossibility = random.nextInt(150);
        Item item;
        if (itemPossibility < 80) {
            item = new BallItem(0, 0);
            oigArrayList.add(new BallItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 110) {
            item = new PowerItem(0, 0);
            oigArrayList.add(new PowerItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 140) {
            item = new PowerItem(0, 0);
            oigArrayList.add(new PowerItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 145){
            item = new EarthquakeItem();
            oigArrayList.add(new Brick(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickInitialX ,brickInitialY ,brickWidth ,brickHeight ,currentBricksHP ,(SpecialItem) item));
            ((EarthquakeItem) item).setBrick((Brick) oigArrayList.get(oigArrayList.size() - 1));
            oigArrayList.add(item);
        }
        else {
            item = new DanceLightItem();
            oigArrayList.add(new Brick(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickInitialX ,brickInitialY ,brickWidth ,brickHeight ,currentBricksHP ,(SpecialItem) item));
            ((DanceLightItem) item).setBrick((Brick) oigArrayList.get(oigArrayList.size() - 1));
            oigArrayList.add(item);
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
            double cx = aimingFirstPoint.getX(), cy = aimingFirstPoint.getY();
            boolean flag = false;
            for (double j = 0 ;j < BricksBreaker.GAME_HEIGHT ;j++){
                if (flag)
                    break;
                cx -= 1 / m;
                cy --;
                for (int i = 0 ;i < oigArrayList.size() ;i++){
                    if (oigArrayList.get(i) instanceof Brick){
                        if (((Brick)oigArrayList.get(i)).collision(cx ,cy ,1) || cx < 0 || cx > BricksBreaker.GAME_WIDTH || cy < 0){
                            flag = true;
                        }
                    }
                }
            }
            if (cy > GAME_HEIGHT - brickInitialY * 2)
                return;
            aimingSecondPoint.setLocation(cx ,cy);
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
            if (oigArrayList.get(i) instanceof Brick || oigArrayList.get(i) instanceof Item) {
                oigArrayList.get(i).setY(oigArrayList.get(i).getY() + brickInitialY * 2);
            }
        }
    }
}
