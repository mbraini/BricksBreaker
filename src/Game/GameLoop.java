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
            if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                ((Brick)BricksBreaker.oigArrayList.get(i)).gravity();
            }
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                ((Ball)BricksBreaker.oigArrayList.get(i)).move();
                System.out.print(((Ball)BricksBreaker.oigArrayList.get(i)).dPoint.getX() + "   ");
                System.out.println(((Ball)BricksBreaker.oigArrayList.get(i)).dPoint.getY());
            }
        }
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
    }

}
