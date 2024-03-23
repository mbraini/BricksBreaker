package Panels;

import Game.*;
import Game.GameLoop;
import Items.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
    public static final int brickInitialX = 50;
    public static final int brickInitialY = 40;
    public static int ballVelocity = 7;
    public static int ballPower = 1;
    public static int ballCount = 1;
    public static int currentBallAimed = 0;
    public static int ballRadios = 10;
    public static int itemRadios = 10;
    public static int gravity = 1;
    public static int brickWidth = GAME_WIDTH / 6;
    public static int brickHeight = GAME_HEIGHT / 9;
    public static int currentBricksHP = 1;
    public static int brickHPD = 1;
    public static int OIGCount;
    public static boolean isRunning;
    public static boolean inTurn = false;
    public static boolean newAim = false;
    public static boolean dizzy = false;
    public static Point aimingFirstPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
    public static Point aimingSecondPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
    public static Point newAimPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
    public static ArrayList<OIG> oigArrayList;
    public static Timer ballAimingTimer;
    public static final int ballAimingDelay = 70;
    public static Color ballItemColor = Color.GREEN;
    public static Color speedItemColor = Color.RED;
    public static Color powerItemColor = Color.BLUE;
    public static Color dizzyItemColor = Color.YELLOW;
    public GameLoop gameLoop;
    public static Clip clip;

    public BricksBreaker(){
        this.setLayout(null);
        this.setBounds(0,Game.GAME_HEIGHT / 10, Game.GAME_WIDTH, Game.GAME_HEIGHT * 9 / 10);
        this.setBackground(backgroundColor);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Game/Song.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e){
            System.out.println("Song Not Found");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0 ;i <oigArrayList.size() ;i++){
            if (oigArrayList.get(i).isVisible) {
                oigArrayList.get(i).draw(g);
            }
        }
        if (!inTurn && Game.Aiming && isRunning){
            paintAiming(g);
        }
    }

    public void start(){
        if (Game.SongTheme){
            clip.setMicrosecondPosition(0);
            clip.start();
        }

        if (difficulty == null){
            difficulty = "Medium";
        }
        oigArrayList = new ArrayList<>();
        gameLoop = new GameLoop(this);
        isRunning = true;
        if (difficulty.equals("Easy")){
            OIGCount = 3;
        }
        else if (difficulty.equals("Medium")){
            OIGCount = 4;
        }
        else {
            OIGCount = 5;
        }

        GamePanel.pt.start();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        addBricksAndItems();
        this.grabFocus();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p'){
                    isRunning = false;
                    gameLoop = null;
                    PT.pause.setBackground(Color.RED);
                    if (Game.SongTheme)
                        clip.stop();
                }
                else if (e.getKeyChar() == 'R' || e.getKeyChar() == 'r'){
                    if (!isRunning) {
                        isRunning = true;
                        gameLoop = new GameLoop(GamePanel.bricksBreaker);
                        gameLoop.start();
                        PT.pause.setBackground(Color.WHITE);
                        if (Game.SongTheme)
                            clip.start();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        gameLoop.start();
    }

    public void addBricksAndItems(){
        ArrayList<Integer> randoms = GameHelper.GenerateRandomBrickLocation(OIGCount);
        for (int i =0 ;i < randoms.size() - 1 ;i++){
            oigArrayList.add(new Brick(randoms.get(i) * GAME_WIDTH/6 + brickInitialX ,brickInitialY ,brickWidth ,brickHeight ,currentBricksHP ,null));
        }
        Random random = new Random();
        int itemPossibility = random.nextInt(200);
        Item item;
        if (itemPossibility < 90) {
            item = new BallItem(0, 0);
            oigArrayList.add(new BallItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 120) {
            item = new PowerItem(0, 0);
            oigArrayList.add(new PowerItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 150) {
            item = new SpeedItem(0, 0);
            oigArrayList.add(new SpeedItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 180) {
            item = new DizzyItem(0, 0);
            oigArrayList.add(new DizzyItem(randoms.get(randoms.size() - 1) * GAME_WIDTH/6 + brickWidth / 2 ,brickHeight / 2));
        }
        else if (itemPossibility < 190){
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

            if (dizzy){
                Random random = new Random();
                int xr = random.nextInt(GAME_WIDTH - 20) + 25;
                int yr = random.nextInt(GAME_HEIGHT - brickInitialY * 2 - 20) + 25;
                m = (aimingFirstPoint.getY() - yr) / (aimingFirstPoint.getX() - xr);
            }
            boolean flag = false;
            for (double j = 0 ;j < BricksBreaker.GAME_HEIGHT ;j++){
                if (flag)
                    break;
                cx -= 1 / m;
                cy --;
                for (int i = 0 ;i < oigArrayList.size() ;i++){
                    if (oigArrayList.get(i) instanceof Brick){
                        if (((Brick)oigArrayList.get(i)).collision(cx ,cy ,0) || cx < 0 || cx > BricksBreaker.GAME_WIDTH || cy < 0){
                            flag = true;
                        }
                    }
                }
            }
            if (cy > GAME_HEIGHT - brickInitialY * 2) {
                for (int i = 0 ;i < oigArrayList.size() ;i++){
                    if (oigArrayList.get(i) instanceof Brick){
                        if (((Brick)oigArrayList.get(i)).collision(cx ,cy ,0)){
                            aimingSecondPoint.setLocation(cx ,cy);
                        }
                    }
                }
                return;
            }
            aimingSecondPoint.setLocation(cx ,cy);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!inTurn && aimingSecondPoint.x != aimingFirstPoint.x && aimingSecondPoint.y != aimingFirstPoint.y && isRunning){
            if (dizzy){
                dizzy = false;
            }
            inTurn = true;
            int ballCountClone = ballCount;
            ballAimingTimer = new Timer(ballAimingDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isRunning) {
                        if (BricksBreaker.inTurn && BricksBreaker.currentBallAimed != ballCountClone) {
                            double x, y;
                            y = BricksBreaker.aimingSecondPoint.y - BricksBreaker.aimingFirstPoint.y;
                            x = BricksBreaker.aimingSecondPoint.x - BricksBreaker.aimingFirstPoint.x;
                            double m = x / y;
                            double yVelocity = Math.sqrt(Math.pow(BricksBreaker.ballVelocity, 2) / (Math.pow(m, 2) + 1));
                            double xVelocity = Math.sqrt(Math.pow(BricksBreaker.ballVelocity, 2) - Math.pow(yVelocity, 2));
                            BricksBreaker.oigArrayList.add(new Ball(
                                    BricksBreaker.aimingFirstPoint.x,
                                    BricksBreaker.aimingFirstPoint.y,
                                    xVelocity * (x / Math.abs(x)),
                                    yVelocity * (y / Math.abs(y)),
                                    BricksBreaker.ballRadios
                            ));
                            ((Ball)oigArrayList.get(oigArrayList.size() - 1)).move();
                            BricksBreaker.currentBallAimed++;
                        } else {
                            BricksBreaker.ballAimingTimer.stop();
                        }
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

    public void stop(){
        gameLoop.interrupt();
        if (SpeedItem.ability != null) {
            SpeedItem.ability.removeActionListener(SpeedItem.speedAL);
            SpeedItem.ability.removeActionListener(SpeedItem.speedAL);
        }

        if (DanceLightItem.timerColor != null) {
            DanceLightItem.timerVisibility.stop();
            DanceLightItem.timerColor.stop();
        }

        if (EarthquakeItem.brickChanger != null) {
            EarthquakeItem.brickChanger.stop();
        }

        if (PowerItem.ability != null){
            PowerItem.ability.stop();
        }
        if (ballAimingTimer != null) {
            ballAimingTimer.stop();
        }

        gameLoop = null;
        brickColor = Color.WHITE;
        backgroundColor = Color.BLACK;
        ballVelocity = 7;
        ballPower = 1;
        ballCount = 1;
        currentBallAimed = 0;
        ballRadios = 10;
        itemRadios = 10;
        gravity = 1;
        brickWidth = GAME_WIDTH / 6;
        brickHeight = GAME_HEIGHT / 9;
        currentBricksHP = 1;
        isRunning = false;
        inTurn = false;
        newAim = false;
        dizzy = false;
        aimingFirstPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);
        aimingSecondPoint = new Point(GAME_WIDTH / 2 ,GAME_HEIGHT);


        oigArrayList = null;
        ballAimingTimer = null;
        ballColor = DanceLightItem.ballColor;
        ballItemColor = Color.GREEN;
        speedItemColor = Color.RED;
        powerItemColor = Color.BLUE;
        dizzyItemColor = Color.YELLOW;

        if (Game.SongTheme){
            clip.stop();
        }
    }
}
