package Game;

import Items.Brick;
import Panels.BricksBreaker;
import Panels.GamePanel;

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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        bricksBreaker.repaint();
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                ((Brick)(BricksBreaker.oigArrayList.get(i))).gravity();
            }
        }
    }

}
