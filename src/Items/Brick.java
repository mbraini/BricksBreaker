package Items;

import Interfaces.Gravity;
import Panels.BricksBreaker;

import java.awt.*;
import java.util.ArrayList;

public class Brick extends OIG implements Gravity {
    int width;
    int height;
    int HP;

    public Brick(int x ,int y ,int width ,int height ,int HP){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.HP = HP;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(BricksBreaker.brickColor);
        g.fillRect(x ,y ,BricksBreaker.brickWidth - 3 ,BricksBreaker.brickHeight - 3);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Default",Font.BOLD ,20));
        g.drawString("" + this.getHP() , x + BricksBreaker.brickWidth / 2, y + BricksBreaker.brickHeight / 2);
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

    public boolean TopAndBottCollision(Ball ball){
        int xm = getX() + BricksBreaker.brickWidth/2 ,ym = getY() + BricksBreaker.brickHeight/2;
        if (Math.abs(ball.getY() - ym) <= BricksBreaker.brickHeight/2 + BricksBreaker.ballRadios && Math.abs(ball.getX() - xm) <= BricksBreaker.brickWidth/2){
            return true;
        }
        return false;
    }

    public boolean LeftAndRightCollision(Ball ball){
        int xm = getX() + BricksBreaker.brickWidth/2 ,ym = getY() + BricksBreaker.brickHeight/2;
        if (Math.abs(ball.getX() - xm) <= BricksBreaker.brickWidth/2 + BricksBreaker.ballRadios && Math.abs(ball.getY() - ym) <= BricksBreaker.brickHeight/2){
            return true;
        }
        return false;
    }

    public boolean CornersCollision(Ball ball){
        int xm = getX(),ym = getY();
        if (ball.getX() < xm && ball.getY() < ym && Math.sqrt(Math.pow(ball.getX()-xm,2) + Math.pow(ball.getY()-ym ,2)) <= BricksBreaker.ballRadios ){
            return true;
        }

        xm = getX() + BricksBreaker.brickWidth; ym = getY();
        if (ball.getX() > xm && ball.getY() < ym && Math.sqrt(Math.pow(ball.getX()-xm,2) + Math.pow(ball.getY()-ym ,2)) <= BricksBreaker.ballRadios){
            return true;
        }

        xm = getX() + BricksBreaker.brickWidth; ym = getY() + BricksBreaker.brickHeight;
        if (ball.getX() > xm && ball.getY() > ym && Math.sqrt(Math.pow(ball.getX()-xm,2) + Math.pow(ball.getY()-ym ,2)) <= BricksBreaker.ballRadios){
            return true;
        }

        xm =getX() ;ym = getY() + BricksBreaker.brickHeight;
        if (ball.getX() < xm && ball.getY() > ym && Math.sqrt(Math.pow(ball.getX()-xm,2) + Math.pow(ball.getY()-ym ,2)) <= BricksBreaker.ballRadios){
            return true;
        }

        return false;
    }
}
