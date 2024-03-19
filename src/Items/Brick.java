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
    SpecialItem specialItem;

    public Brick(int x ,int y ,int width ,int height ,int HP ,SpecialItem specialItem){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.HP = HP;
        HPI = HP;
        this.specialItem = specialItem;
        isVisible = true;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.brickColor);
        g.fillRect((int) (x - BricksBreaker.brickWidth / 2) ,(int) (y - BricksBreaker.brickHeight / 2) ,BricksBreaker.brickWidth - 3 ,BricksBreaker.brickHeight - 3);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Default",Font.BOLD ,20));
        g.drawString("" + this.getHP() , (int) (x) ,(int) (y));
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

    public boolean collision(double x ,double y) {
        if (Math.abs(x - this.x) <= BricksBreaker.brickWidth / 2d + BricksBreaker.ballRadios && Math.abs(y - this.y) <= BricksBreaker.brickHeight / 2d + BricksBreaker.ballRadios) {
            return true;
        }
        return false;
    }

    public void Break(){
        GamePanel.pt.addPoint(HPI - GamePanel.pt.getTime() * (0.1) / 1000);
        if (specialItem != null){
            specialItem.ability();
            BricksBreaker.oigArrayList.remove(specialItem);
        }
        BricksBreaker.oigArrayList.remove(this);
    }

    public void decreesHP(){
        HP--;
    }

    public SpecialItem getSpecialItem() {
        return specialItem;
    }

    public void setSpecialItem(SpecialItem specialItem) {
        this.specialItem = specialItem;
    }
}
