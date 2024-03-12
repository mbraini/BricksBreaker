package Items;

import Game.MyPoint;
import Interfaces.Moveable;
import Panels.BricksBreaker;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends OIG implements Moveable {
    int radios;
    public MyPoint dPoint;

    public Ball(int x ,int y ,double xVelocity ,double yVelocity ,int radios){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        dPoint = new MyPoint(x ,y);
        this.radios = radios;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.ballColor);
        g.fillOval(x - BricksBreaker.ballRadios ,y - BricksBreaker.ballRadios ,BricksBreaker.ballRadios * 2 ,BricksBreaker.ballRadios * 2);
    }

    public int getRadios() {
        return radios;
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }

    @Override
    public void move() {
        dPoint.setX(dPoint.getX() + xVelocity);
        dPoint.setY(dPoint.getY() + yVelocity);
        x = (int)(dPoint.getX());
        y = (int)(dPoint.getY());
    }
}
