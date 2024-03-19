package Items;

import Game.Game;
import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EarthquakeItem extends SpecialItem{

    static Timer brickChanger;
    static boolean flag = false;
    static double time;

    @Override
    public void gravity() {
        this.y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        reset();
        if (brickChanger != null){
            if (brickChanger.isRunning()){
                brickChanger.stop();
            }
        }
        brickChanger = new Timer(125, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                    if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                        Brick brick = (Brick) BricksBreaker.oigArrayList.get(i);
                        for (int j = 0 ;j < BricksBreaker.oigArrayList.size() ;j++){
                            if (BricksBreaker.oigArrayList.get(j) instanceof Ball){
                                Ball ball = (Ball) BricksBreaker.oigArrayList.get(j);
                                if (brick.collision(ball)){
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!flag){
                    BricksBreaker.brickWidth -= 3;
                    BricksBreaker.brickHeight -= 2;
                }
                else {
                    BricksBreaker.brickWidth += 3;
                    BricksBreaker.brickHeight += 2;
                }
                if (BricksBreaker.brickWidth < 40 || BricksBreaker.brickWidth > 100){
                    flag = !flag;
                }
                if ((PT.time - time) / 1000 >= 10){
                    reset();
                    brickChanger.stop();
                }
            }
        });
        brickChanger.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Default",Font.BOLD ,14));
        g.drawString("EARTHQUAKE" ,(int) (brick.getX() + BricksBreaker.brickWidth / 30) ,(int) (brick.getY() + BricksBreaker.brickHeight / 4 * 3));
    }

    void reset(){
        time = PT.time;
        BricksBreaker.brickWidth = 100;
        BricksBreaker.brickHeight = 80;
    }
}
