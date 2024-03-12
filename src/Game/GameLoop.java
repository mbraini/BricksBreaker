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
//                ((Brick)BricksBreaker.oigArrayList.get(i)).gravity();
            }
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                if (ball.getX() <= BricksBreaker.ballRadios || ball.getX() >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios) {
                    ball.setxVelocity(-ball.getxVelocity());
                }
                else if (ball.getY() <= BricksBreaker.ballRadios){
                    ball.setyVelocity(-ball.getyVelocity());
                }

                ///////////////////////////

                for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++){
                    if (BricksBreaker.oigArrayList.get(j) instanceof Brick){
                        if (((Brick)BricksBreaker.oigArrayList.get(j)).CornersCollision((Ball)BricksBreaker.oigArrayList.get(i))){
                            ((Ball)BricksBreaker.oigArrayList.get(i)).setyVelocity(-((Ball)BricksBreaker.oigArrayList.get(i)).getyVelocity());
                            ((Ball)BricksBreaker.oigArrayList.get(i)).setxVelocity(-((Ball)BricksBreaker.oigArrayList.get(i)).getxVelocity());
                            ((Ball)BricksBreaker.oigArrayList.get(i)).move();
                            ((Brick)BricksBreaker.oigArrayList.get(j)).setHP(((Brick)BricksBreaker.oigArrayList.get(j)).getHP() - 1);
                        }
                        else if (((Brick)BricksBreaker.oigArrayList.get(j)).TopAndBottCollision((Ball)BricksBreaker.oigArrayList.get(i))){
                            ((Ball)BricksBreaker.oigArrayList.get(i)).setyVelocity(-((Ball)BricksBreaker.oigArrayList.get(i)).getyVelocity());
                            ((Ball)BricksBreaker.oigArrayList.get(i)).move();
                            ((Brick)BricksBreaker.oigArrayList.get(j)).setHP(((Brick)BricksBreaker.oigArrayList.get(j)).getHP() - 1);
                        }
                        else if (((Brick)BricksBreaker.oigArrayList.get(j)).LeftAndRightCollision((Ball)BricksBreaker.oigArrayList.get(i))){
                            ((Ball)BricksBreaker.oigArrayList.get(i)).setxVelocity(-((Ball)BricksBreaker.oigArrayList.get(i)).getxVelocity());
                            ((Ball)BricksBreaker.oigArrayList.get(i)).move();
                            ((Brick)BricksBreaker.oigArrayList.get(j)).setHP(((Brick)BricksBreaker.oigArrayList.get(j)).getHP() - 1);
                        }
                    }
                }

                ///////////////////////////
                ((Ball)BricksBreaker.oigArrayList.get(i)).move();
            }
        }
    }

}
