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
    public ArrayList<Point> getMargin() {
        ArrayList<Point> margin = new ArrayList<>();
        int x,y;
        for (int i = 0 ;i < 360 ;i++){
            x = (int)(this.x + radios * Math.cos(Math.toRadians(i)));
            y = (int)(this.y + radios * Math.sin(Math.toRadians(i)));
            margin.add(new Point(x,y));
        }
        return margin;
    }

    public boolean collision(OIG a){
        ArrayList<Point> ballMargin = getMargin();
        ArrayList<Point> aMargin = a.getMargin();
        for (int i = 0 ;i < ballMargin.size() ;i++){
            for (int j = 0 ;j < aMargin.size() ;j++){
                if (ballMargin.get(i).getX() == aMargin.get(j).getX() && ballMargin.get(i).getY() == aMargin.get(j).getY()){
                    return true;
                }
            }
        }
        return false;
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
