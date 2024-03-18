package Items;

import Game.Game;
import Panels.BricksBreaker;

import java.awt.*;

public class EarthquakeItem extends SpecialItem{

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
    }
}
