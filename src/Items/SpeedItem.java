package Items;

import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedItem extends OrdinaryItem{
    static public Timer ability ;
    static public SpeedAL speedAL = new SpeedAL(null);
    static public double time;

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
        time = PT.time;
        double V;
        BricksBreaker.ballVelocity = 7;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                V = Math.sqrt( Math.pow(ball.getxVelocity() ,2) + Math.pow(ball.getyVelocity() ,2) );
                ball.setxVelocity(ball.getxVelocity() * (7d /V));
                ball.setyVelocity(ball.getyVelocity() * (7d /V));
            }
        }
        ability = new Timer(100, speedAL);
        speedAL.ability = ability;
        BricksBreaker.ballVelocity = 14;
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                Ball ball = (Ball)BricksBreaker.oigArrayList.get(i);
                V = Math.sqrt( Math.pow(ball.getxVelocity() ,2) + Math.pow(ball.getyVelocity() ,2) );
                ball.setxVelocity(ball.getxVelocity() * (14d /V));
                ball.setyVelocity(ball.getyVelocity() * (14d /V));
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
