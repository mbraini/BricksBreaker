package Items;

import Game.Game;
import Panels.BricksBreaker;

import java.awt.*;

public class EarthquakeItem extends SpecialItem{

    public EarthquakeItem(Brick brick) {
        this.x = brick.getX();
        this.y = brick.getY();
    }

    @Override
    public void gravity() {
        this.y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        //////////////ToDo /////////////////
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.earthquake ,x ,y ,BricksBreaker.brickWidth ,BricksBreaker.brickHeight ,null ,null);
    }
}
