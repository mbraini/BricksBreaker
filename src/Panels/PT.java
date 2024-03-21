package Panels;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PT extends JPanel {
    public static JLabel pause;
    public static JLabel resume;
    JLabel timeL;
    JLabel pointL;
    Timer timer;
    static public double time;
    double point;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        PT.time = time;
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
        
        initPause();
        initResume();
    }

    private void initPause() {
        pause = new JLabel();
        pause.setBounds(getWidth() * 4 / 5,0 ,getWidth() / 5,getHeight() / 2);
        pause.setText("Pause : P");
        pause.setBackground(Color.WHITE);
        pause.setOpaque(true);
        pause.setHorizontalAlignment(JLabel.CENTER);
        pause.setVerticalAlignment(JLabel.CENTER);
        this.add(pause);
    }

    private void initResume() {
        resume = new JLabel();
        resume.setBounds(getWidth() * 4 / 5,getHeight() / 2 ,getWidth() / 5,getHeight() / 2);
        resume.setText("Resume : R");
        resume.setBackground(Color.WHITE);
        resume.setOpaque(true);
        resume.setHorizontalAlignment(JLabel.CENTER);
        resume.setVerticalAlignment(JLabel.CENTER);
        this.add(resume);
    }

    public void addTime(double time){
        PT.time += time;
        timeL.setText("time : " + (int)(PT.time / 1000));
    }

    public void addPoint(double point){
        this.point += point;
        pointL.setText("points : " + (int)this.point);
    }

    public void start() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BricksBreaker.isRunning) {
                    addTime(1000);
                }
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


    public void stop(){
        timer.stop();
        time = 0;
        point = 0;
        timer = null;
    }
}
