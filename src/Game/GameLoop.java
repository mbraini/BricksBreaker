package Game;

import Items.Ball;
import Items.Brick;
import Items.Item;
import Panels.BricksBreaker;

public class GameLoop extends Thread{

    BricksBreaker bricksBreaker;

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
        }
    }

    public void update(){
        bricksBreaker.repaint();
        if (!BricksBreaker.inTurn) {
            for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                    ((Brick)BricksBreaker.oigArrayList.get(i)).gravity();
                }
                if (BricksBreaker.oigArrayList.get(i) instanceof Item){
                    ((Item)BricksBreaker.oigArrayList.get(i)).move();
                }
            }
        }

        else {
            for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                    Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                    checkBorderCollision(ball);
                    ball.move();
                    for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++){
                        if (BricksBreaker.oigArrayList.get(j) instanceof Brick){
                            Brick brick = (Brick) BricksBreaker.oigArrayList.get(j);
                            BrickCollisionCheck(brick ,ball);
                        }
                    }

                    ///////////ball reaches The End

                    if (ball.getY() >= BricksBreaker.GAME_HEIGHT) {
                        BricksBreaker.oigArrayList.remove(i);
                        if (BallsRemoved()){
                            BricksBreaker.inTurn = false;
                            bricksBreaker.addBricks();
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
                if (((Brick)BricksBreaker.oigArrayList.get(i)).getHP() == 0){
                    ((Brick)BricksBreaker.oigArrayList.get(i)).Break();
                }
            }
        }
    }

    void checkBorderCollision(Ball ball){
        if (ball.getX() <= BricksBreaker.ballRadios || ball.getX() >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios) {
            ball.setxVelocity(-ball.getxVelocity());
        }
        else if (ball.getY() <= BricksBreaker.ballRadios){
            ball.setyVelocity(-ball.getyVelocity());
        }
    }

    void BrickCollisionCheck(Brick brick ,Ball ball){
        if (brick.CornersCollision(ball)){
            ball.setxVelocity(-ball.getxVelocity());
            ball.setyVelocity(-ball.getyVelocity());
            brick.setHP(brick.getHP() - 1);
            ball.move();
        }
        else if (brick.TopAndBottCollision(ball)){
            ball.setyVelocity(-ball.getyVelocity());
            brick.setHP(brick.getHP() - 1);
            ball.move();
        }
        else if (brick.LeftAndRightCollision(ball)){
            ball.setxVelocity(-ball.getxVelocity());
            brick.setHP(brick.getHP() - 1);
            ball.move();
        }

    }

}
