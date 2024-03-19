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
        brickChanger = new Timer(31, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!flag){
                    BricksBreaker.brickWidth--;
                    BricksBreaker.brickHeight--;
                }
                else {
                    BricksBreaker.brickWidth++;
                    BricksBreaker.brickHeight++;
                }
                if (BricksBreaker.brickWidth < 60 || BricksBreaker.brickWidth > 100){
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
