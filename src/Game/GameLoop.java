package Game;

import Items.Ball;
import Items.Brick;
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
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Brick && !BricksBreaker.inTurn){
                ((Brick)BricksBreaker.oigArrayList.get(i)).gravity();
            }
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                if (ball.getX() <= BricksBreaker.ballRadios || ball.getX() >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios) {
                    ball.setxVelocity(-ball.getxVelocity());
                }
                if (ball.getY() <= BricksBreaker.ballRadios){
                    ball.setyVelocity(-ball.getyVelocity());
                }

                ///////////////////////////


                ///////////////////////////
                ((Ball)BricksBreaker.oigArrayList.get(i)).move();
            }
        }
    }

}
