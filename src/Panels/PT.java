package Panels;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PT extends JPanel {
    JLabel timeL;
    JLabel pointL;
    Timer timer;
    static public double time;
    double point;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

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
        this.point += point;
        pointL.setText("points : " + (int)this.point);
    }

    public void start() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTime(1000);
            }
        });
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

        timer.start();
    }
}
