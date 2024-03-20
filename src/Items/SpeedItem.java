package Items;

import Panels.BricksBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedItem extends OrdinaryItem{
    static Timer ability ;
    static SpeedAL speedAL = new SpeedAL(null);

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
            ability.removeActionListener(speedAL);
        }
        double V;
        BricksBreaker.ballVelocity = 6;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                V = Math.sqrt( Math.pow(ball.getxVelocity() ,2) + Math.pow(ball.getyVelocity() ,2) );
                ball.setxVelocity(ball.getxVelocity() * (6d /V));
                ball.setyVelocity(ball.getyVelocity() * (6d /V));
            }
        }
        ability = new Timer(15000, speedAL);
        speedAL.ability = ability;
        BricksBreaker.ballVelocity = 12;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                V = Math.sqrt( Math.pow(ball.getxVelocity() ,2) + Math.pow(ball.getyVelocity() ,2) );
                ball.setxVelocity(ball.getxVelocity() * (12d /V));
                ball.setyVelocity(ball.getyVelocity() * (12d /V));
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
