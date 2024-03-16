package Items;

import Game.Game;
import Panels.BricksBreaker;

import java.awt.*;

public class DanceLightItem extends SpecialItem{

    public DanceLightItem(Brick brick) {
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
        g.drawImage(Game.danceLight ,x ,y ,BricksBreaker.brickWidth ,BricksBreaker.brickHeight ,null ,null);
    }
}
