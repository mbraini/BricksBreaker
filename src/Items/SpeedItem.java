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

//        this.ability = new Timer(15000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                BricksBreaker.ballVelocity = 8;
//                ability.stop();
//            }
//        });
    }

    @Override
    public void gravity() {
        y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
//        BricksBreaker.ballVelocity = 16;
//        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
//            if (BricksBreaker.oigArrayList.get(i) instanceof Ball) {
//                BricksBreaker.oigArrayList.get(i).setxVelocity(BricksBreaker.oigArrayList.get(i).getxVelocity() * Math.pow(2, 0.5));
//                BricksBreaker.oigArrayList.get(i).setyVelocity(BricksBreaker.oigArrayList.get(i).getyVelocity() * Math.pow(2, 0.5));
//            }
//        }
//        ability.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.speedItemColor);
        g.fillOval(x - BricksBreaker.itemRadios ,y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
