package Items;

import Game.MyPoint;
import Interfaces.Moveable;
import Panels.BricksBreaker;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends OIG implements Moveable {
    int radios;

    public Ball(int x ,int y ,double xVelocity ,double yVelocity ,int radios){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.radios = radios;
        isVisible = true;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.ballColor);
        g.fillOval((int) (x - BricksBreaker.ballRadios) ,(int) (y - BricksBreaker.ballRadios) ,BricksBreaker.ballRadios * 2 ,BricksBreaker.ballRadios * 2);
    }

    public int getRadios() {
        return radios;
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }

    @Override
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void collisionFix(ArrayList<Brick> bricks) {
        double xI = x;
        double yI = y;
        double xVelocityI = xVelocity;
        double yVelocityI = yVelocity;


        yVelocity = -yVelocity;
        move();
        if (hasCollision(bricks) || borderCollision()){
            reset(xI ,yI ,xVelocityI ,yVelocityI);

            xVelocity = -xVelocity;
            move();
            if (hasCollision(bricks) || borderCollision()){
                reset(xI ,yI ,xVelocityI ,yVelocityI);

                xVelocity = -xVelocity;
                yVelocity = -yVelocity;
                move();
                if (hasCollision(bricks) || borderCollision()){
                    BricksBreaker.oigArrayList.remove(this);
                    System.out.println("JZZZZZZZZZZZZ");
                }
                else {
                    for (int i = 0; i < bricks.size() ;i++){
                        bricks.get(i).decreesHP();
                    }
                    reset(xI ,yI ,xVelocityI ,yVelocityI);
                    xVelocity = -xVelocity;
                    yVelocity = -yVelocity;
                }
            }
            else {
                for (int i = 0; i < bricks.size() ;i++){
                    bricks.get(i).decreesHP();
                }
                reset(xI ,yI ,xVelocityI ,yVelocityI);
                xVelocity = -xVelocity;
            }
        }
        else {
            for (int i = 0; i < bricks.size() ;i++){
                bricks.get(i).decreesHP();
            }
            reset(xI ,yI ,xVelocityI ,yVelocityI);
            yVelocity = -yVelocity;
        }
    }

    boolean hasCollision(ArrayList<Brick> bricks){
        for (int i = 0 ;i < bricks.size() ;i++){
            if (bricks.get(i).collision(this)){
                return true;
            }
        }
        return false;
    }

    void reset(double x ,double y ,double xVelocity ,double yVelocity){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    boolean borderCollision(){
        if (x <= BricksBreaker.ballRadios || x >= BricksBreaker.GAME_WIDTH - BricksBreaker.ballRadios || y <= BricksBreaker.ballRadios) {
            return true;
        }
        return false;
    }
}
