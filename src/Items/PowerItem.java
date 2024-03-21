package Items;

import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerItem extends OrdinaryItem{
    public static Timer ability;
    public static double time;

    public PowerItem(int x ,int y) {
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
            if (ability.isRunning()){
                ability.stop();
            }
        }
        time = PT.time;
        ability = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BricksBreaker.isRunning && PT.time - time > 15000) {
                    BricksBreaker.ballPower = 1;
                    ability.stop();
                }
            }
        });
        BricksBreaker.ballPower = 2;
        ability.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.powerItemColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
