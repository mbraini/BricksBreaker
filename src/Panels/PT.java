package Panels;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class PT extends JPanel {
    JLabel timeL;
    JLabel pointL;
    double time;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    int point;

    public PT(){
        this.setLayout(null);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT / 10);
        this.setBackground(Color.YELLOW);
    }
    public void addTime(double time){
        this.time += time;
        timeL.setText("time : " + (int)(this.time / 1000));
    }

    public void addPoint(double point){
        this.point += (int) point;
        pointL.setText("points : " + this.point);
    }

    public void start() {
        time = 0;
        point = 0;

        timeL = new JLabel();
        timeL.setBounds(getWidth() / 5 ,getHeight() / 5 ,getWidth() / 5 ,getHeight() * 3 / 5);
        timeL.setText("time : " + (int)(time / 1000));

        pointL = new JLabel();
        pointL.setBounds(getWidth() * 3 / 5 ,getHeight() / 5 ,getWidth() / 5 ,getHeight() * 3 / 5);
        pointL.setText("points : " + point);

        this.add(timeL);
        this.add(pointL);


    }
}
