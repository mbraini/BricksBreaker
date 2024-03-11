package Items;

import java.awt.*;
import java.util.ArrayList;

public class Brick extends OIG{
    int width;
    int height;

    @Override
    public ArrayList<Point> getMargin() {
        ArrayList<Point> margin = new ArrayList<>();
        int x,y;
        for (int i = 0 ;i < width ;i++){
            x = this.x - (this.width/2) + i;
            margin.add(new Point(x ,this.y - (this.height/2)));
            margin.add(new Point(x ,this.y + (this.height/2)));
        }
        for (int i = 0 ;i < height ;i++){
            y = this.y - (this.height/2) + i;
            margin.add(new Point(this.x - (this.width/2) ,y));
            margin.add(new Point(this.x + (this.width/2) ,y));
        }
        return margin;
    }


    @Override
    public void draw(Graphics g) {
    }

}
