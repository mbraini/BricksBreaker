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
        int randomR = random.nextInt(26) - 13;
        int randomG = random.nextInt(26) - 13;
        int randomB = random.nextInt(26) - 13;
        r += randomR;
        g += randomG;
        b += randomB;
        if (r > 255){
            r -=255;
        }
        if (g > 255){
            g -=255;
        }
        if (b > 255){
            b -=255;
        }

        if (r < 0){
            r += 255;
        }
        if (g < 0){
            g += 255;
        }
        if (b < 0){
            b += 255;
        }
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
