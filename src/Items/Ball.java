package Items;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends OIG{
    int radios;

    public Ball(int x ,int y ,int radios){
        this.x = x;
        this.y = y;
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
        g.drawOval(x ,y ,radios ,radios);
    }

    public int getRadios() {
        return radios;
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }
}
