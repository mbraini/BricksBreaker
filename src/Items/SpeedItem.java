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

        this.ability = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("dgkfsdkgf");
                BricksBreaker.ballVelocity = 8;
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
        BricksBreaker.ballVelocity = 16;
        ability.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.speedItemColor);
        g.fillOval(x - BricksBreaker.itemRadios ,y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
