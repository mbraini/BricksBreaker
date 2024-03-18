package Items;

import Panels.BricksBreaker;

import java.awt.*;

public class BallItem extends OrdinaryItem{

    public BallItem(int x ,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void gravity() {
        y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        BricksBreaker.ballCount++;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.ballItemColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
