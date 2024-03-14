package Items;

import Panels.BricksBreaker;

public abstract class OrdinaryItem extends Item{

    public void collision(Ball ball){
        if ( Math.sqrt( Math.pow(ball.getX() - this.x ,2) + Math.pow(ball.getY() - this.y ,2) ) <= BricksBreaker.ballRadios + BricksBreaker.itemRadios){
            this.ability();
            BricksBreaker.oigArrayList.remove(this);
        }
    }

}
