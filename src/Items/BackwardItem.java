package Items;

import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.*;

public class BackwardItem extends OrdinaryItem{
    public BackwardItem(int x ,int y) {
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
        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
            if ((BricksBreaker.oigArrayList.get(i) instanceof Brick || BricksBreaker.oigArrayList.get(i) instanceof OrdinaryItem) && BricksBreaker.oigArrayList.get(i) != this){
                if (BricksBreaker.oigArrayList.get(i).getY() <= 2.5 * BricksBreaker.brickWidth){
                    if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                        ((Brick)BricksBreaker.oigArrayList.get(i)).Break();
                    }
                    else if (!(BricksBreaker.oigArrayList.get(i) instanceof BackwardItem)){ //////////////////////
                        ((OrdinaryItem)BricksBreaker.oigArrayList.get(i)).ability();
                        BricksBreaker.oigArrayList.remove(i);
                    }
                    else {
                        BricksBreaker.oigArrayList.remove(i);
                    }
                    i--;
                }
                else {
                    BricksBreaker.oigArrayList.get(i).setY(BricksBreaker.oigArrayList.get(i).getY() - 2 * BricksBreaker.brickHeight);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.backwardColor);
        g.fillOval((int) x - BricksBreaker.itemRadios ,(int) y - BricksBreaker.itemRadios ,BricksBreaker.itemRadios * 2 ,BricksBreaker.itemRadios * 2);
    }
}
