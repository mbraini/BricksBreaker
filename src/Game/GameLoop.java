package Game;

import Items.Ball;
import Items.Brick;
import Items.Item;
import Items.OrdinaryItem;
import Panels.BricksBreaker;
import Panels.EndGamePanel;
import Panels.GamePanel;
import Panels.MainPanel;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameLoop extends Thread{

    BricksBreaker bricksBreaker;
    static public int collision = 0;

    public GameLoop(BricksBreaker bricksBreaker){
        super();
        this.bricksBreaker = bricksBreaker;
    }

    @Override
    public void run() {
        super.run();
        while (BricksBreaker.isRunning){
            try {
                Thread.sleep(BricksBreaker.REFRESH_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            update();
        }
    }

    public void update(){
        bricksBreaker.revalidate();
        bricksBreaker.repaint();
        bricksBreaker.setBackground(BricksBreaker.backgroundColor);
        if (Game.SongTheme && BricksBreaker.isRunning){
            if (!BricksBreaker.clip.isRunning()){
                BricksBreaker.clip.setMicrosecondPosition(0);
                BricksBreaker.clip.start();
                System.out.println("START AGAIN!");
            }
        }
        if (checkEndGame()) {
            GamePanel.stop();
            return;
        }
        if (!BricksBreaker.isRunning){
            return;
        }
        if (!BricksBreaker.inTurn) {
            for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                    ((Brick)BricksBreaker.oigArrayList.get(i)).gravity();
                }
                if (BricksBreaker.oigArrayList.get(i) instanceof Item){
                    ((Item)BricksBreaker.oigArrayList.get(i)).gravity();
                }
            }
        }

        else {
            for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                    Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                    checkBorderCollision(ball);
                    ArrayList<Brick> bricks = new ArrayList<>();
                    for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++) {
                        if (BricksBreaker.oigArrayList.get(j) instanceof Brick) {
                            Brick brick = (Brick) BricksBreaker.oigArrayList.get(j);
                            if (brick.collision(ball.getX() ,ball.getY() ,BricksBreaker.ballRadios)) {
                                bricks.add(brick);
                            }
                        }
                    }
                    if (!bricks.isEmpty()){
                        ball.collisionFix(bricks);
                    }
                    ball.move();
                    for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++){
                        if (BricksBreaker.oigArrayList.get(j) instanceof OrdinaryItem){
                            OrdinaryItem item = (OrdinaryItem) BricksBreaker.oigArrayList.get(j);
                            item.collision(ball);
                        }
                    }
                }
            }
            for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                    Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                    if (ball.getY() >= BricksBreaker.GAME_HEIGHT + BricksBreaker.ballRadios) {
                        if (!BricksBreaker.newAim){
                            double ballX = ball.getX(), ballY = ball.getY();
                            if (ball.getX() <= BricksBreaker.ballRadios + 20){
                                ballX = BricksBreaker.ballRadios + 20;
                            }
                            if (ball.getX() >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios - 20){
                                ballX = BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios - 20;
                            }
                            BricksBreaker.newAimPoint.setLocation(ballX ,ballY);
                            BricksBreaker.newAim = true;
                        }
                        BricksBreaker.oigArrayList.remove(ball);
                        break;
                    }
                }
            }
            checkBricksHP();
            if (BallsRemoved() && !BricksBreaker.ballAimingTimer.isRunning()){
                nextTurn();
            }
        }
    }

    private boolean checkEndGame() {
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                Brick brick = (Brick) BricksBreaker.oigArrayList.get(i);
                if (brick.getY() >= BricksBreaker.GAME_HEIGHT - BricksBreaker.brickHeight / 2d){
                    return true;
                }
            }
        }
        return false;
    }


    private boolean BallsRemoved() {
        int ballCount = 0;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                ballCount++;
            }
        }
        if (ballCount == 0){
            return true;
        }
        else {
            return false;
        }
    }

    void checkBricksHP(){
        for (int i = 0; i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                if (((Brick)BricksBreaker.oigArrayList.get(i)).getHP() <= 0){
                    ((Brick)BricksBreaker.oigArrayList.get(i)).Break();
                }
            }
        }
    }

    void checkBorderCollision(Ball ball){
        if (ball.getX() <= BricksBreaker.ballRadios || ball.getX() >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios) {
            ball.setxVelocity(-ball.getxVelocity());
        }
        if (ball.getY() <= BricksBreaker.ballRadios){
            ball.setyVelocity(-ball.getyVelocity());
        }
    }


    void nextTurn(){
        if (!BricksBreaker.newAim){
            Random random = new Random();
            BricksBreaker.aimingFirstPoint.setLocation(random.nextInt(BricksBreaker.GAME_WIDTH - 4*BricksBreaker.ballRadios) + 2*BricksBreaker.ballRadios ,BricksBreaker.GAME_HEIGHT);
        }
        else {
            BricksBreaker.aimingFirstPoint.setLocation(BricksBreaker.newAimPoint.getX() ,BricksBreaker.newAimPoint.getY());
        }
        BricksBreaker.newAim = false;
        BricksBreaker.ballCount ++;
        BricksBreaker.currentBricksHP++;
        BricksBreaker.inTurn = false;
        bricksBreaker.pushBricks();
        bricksBreaker.addBricksAndItems();
        BricksBreaker.currentBallAimed = 0;
    }

    static public void CheckBallOut(Ball ball){

    }

}
