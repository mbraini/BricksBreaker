package Items;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends OIG{
    int radios;

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


    @Override
    public void draw(Graphics g) {
    }
}
