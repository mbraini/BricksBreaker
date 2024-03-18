package Items;

public abstract class SpecialItem extends Item{
    Brick brick;

    public Brick getBrick() {
        return brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
    }
}
