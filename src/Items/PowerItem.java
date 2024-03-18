package Items;

import Panels.BricksBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerItem extends OrdinaryItem{
    Timer ability;

    public PowerItem(int x ,int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
        this.ability = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BricksBreaker.ballPower = 1;
                ability.stop();
            }
        });
    }

    @Override
    public void gravity() {
        y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        BricksBreaker.ballPower = 2;
        ability.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.powerItemColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
