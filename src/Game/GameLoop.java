package Game;

import Panels.GamePanel;

public class GameLoop extends Thread{

    GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel){
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        super.run();
        while (GamePanel.isRunning){
            update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        System.out.println("HELLO");
    }

}
