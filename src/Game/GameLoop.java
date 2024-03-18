package Game;

import Items.Ball;
import Items.Brick;
import Items.Item;
import Items.OrdinaryItem;
import Panels.BricksBreaker;
import Panels.GamePanel;

import java.util.ArrayList;

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
        while (bricksBreaker.isRunning){
            update();
            try {
                Thread.sleep(BricksBreaker.REFRESH_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GamePanel.pt.addTime(BricksBreaker.REFRESH_RATE);
        }
    }

    public void update(){
        bricksBreaker.revalidate();
        bricksBreaker.repaint();
        bricksBreaker.setBackground(BricksBreaker.backgroundColor);
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
                    ball.move();
                    checkBorderCollision(ball);
                    ArrayList<Brick> bricks = new ArrayList<>();
                    for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++) {
                        if (BricksBreaker.oigArrayList.get(j) instanceof Brick) {
                            Brick brick = (Brick) BricksBreaker.oigArrayList.get(j);
                            if (brick.collision(ball)) {
                                bricks.add(brick);
                            }
                        }
                    }
                    if (!bricks.isEmpty()){
                        ball.collisionFix(bricks);
                    }
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
                    if (ball.getY() >= BricksBreaker.GAME_HEIGHT) {
                        BricksBreaker.oigArrayList.remove(ball);
                        if (BallsRemoved()){
                            nextTurn();
                        }
                        break;
                    }
                }
            }
            checkBricksHP();
        }
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
        BricksBreaker.ballCount ++;
        BricksBreaker.currentBricksHP++;
        BricksBreaker.inTurn = false;
        bricksBreaker.pushBricks();
        bricksBreaker.addBricksAndItems();
    }

    static public void CheckBallOut(Ball ball){

    }

}
