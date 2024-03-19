package Items;

import Panels.BricksBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedItem extends OrdinaryItem{
    Timer ability;

    public SpeedItem(int x ,int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    @Override
    public void gravity() {
        y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        if (ability != null){
            if (ability.isRunning())
                ability.stop();
        }
        ability = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                    if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                        Ball ball =(Ball) (BricksBreaker.oigArrayList.get(i));
                        ball.setxVelocity(ball.getxVelocity() / 2);
                        ball.setyVelocity(ball.getyVelocity() / 2);
                    }
                }
                BricksBreaker.ballVelocity /= 2;
                ability.stop();
            }
        });
        BricksBreaker.ballVelocity *= 2;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball =(Ball) (BricksBreaker.oigArrayList.get(i));
                ball.setxVelocity(ball.getxVelocity() * 2);
                ball.setyVelocity(ball.getyVelocity() * 2);
            }
        }
        ability.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.speedItemColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
