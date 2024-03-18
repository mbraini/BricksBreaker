package Items;

import Game.GameLoop;
import Game.MyPoint;
import Interfaces.Gravity;
import Panels.BricksBreaker;
import Panels.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Brick extends OIG implements Gravity {
    int width;
    int height;
    int HP;
    int HPI;

    public Brick(int x ,int y ,int width ,int height ,int HP){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.HP = HP;
        HPI = HP;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.brickColor);
        g.fillRect((int) x ,(int) y ,BricksBreaker.brickWidth - 3 ,BricksBreaker.brickHeight - 3);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Default",Font.BOLD ,20));
        g.drawString("" + this.getHP() , (int) (x + BricksBreaker.brickWidth / 2) ,(int) (y + BricksBreaker.brickHeight / 2));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void gravity() {
        y += BricksBreaker.gravity;
    }

    public boolean collision(Ball ball) {
        double xc = x + BricksBreaker.brickWidth / 2d;
        double yc = y + BricksBreaker.brickHeight / 2d;
        if (Math.abs(ball.getX() - xc) < BricksBreaker.brickWidth / 2d + BricksBreaker.ballRadios && Math.abs(ball.getY() - yc) < BricksBreaker.brickHeight / 2d + BricksBreaker.ballRadios) {
            return true;
        }
        return false;
    }

    public void Break(){
        GamePanel.pt.addPoint(HPI - GamePanel.pt.getTime() * (0.1) / 1000);
        BricksBreaker.oigArrayList.remove(this);
    }

    public void decreesHP(){
        HP--;
    }
}
