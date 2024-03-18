package Game;

import java.awt.*;
import java.util.Random;

public class MyColor {
    int r;
    int b;
    int g;

    public MyColor(int r ,int g ,int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void add(){
        Random random = new Random();
        int randomR = random.nextInt(256);
        int randomG = random.nextInt(256);
        int randomB = random.nextInt(256);
        r = randomR;
        g = randomG;
        b = randomB;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
