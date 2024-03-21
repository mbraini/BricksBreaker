package Items;

import Panels.BricksBreaker;
import java.awt.*;

public class DizzyItem extends OrdinaryItem{
    static double time;

    public DizzyItem(int x ,int y) {
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
        BricksBreaker.dizzy = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.dizzyItemColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
