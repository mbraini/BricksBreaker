package Items;

import Game.*;
import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DanceLightItem extends SpecialItem{

    static Timer timer;
    static double time = 0;
    static MyColor ballColorT = new MyColor(255 ,255 ,255);
    static MyColor brickColorT = new MyColor(255 ,255 ,255);
    static MyColor backgroundColorT = new MyColor(0 ,0 ,0);
    static MyColor ballItemColorT = new MyColor(0 ,255 ,0);
    static MyColor speedItemColorT = new MyColor(255 ,0 ,0);
    static MyColor powerItemColorT = new MyColor(0 ,0 ,255);
    static boolean itemVisibility;
    static boolean ballVisibility;
    static boolean brickVisibility;

    @Override
    public void gravity() {
        this.y += BricksBreaker.gravity;
    }

    @Override
    void ability() {
        reset();
        if (timer != null){
            if (timer.isRunning())
                timer.stop();
        }
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((PT.time - time) / 1000d <= 10){
                    if (((PT.time - time) / 1000d) % 0.05 < 0.01){
                        itemVisibility = !itemVisibility;
                        ballVisibility = !ballVisibility;
                        brickVisibility = !brickVisibility;
                        for (int i = 0 ;i < BricksBreaker.oigArrayList.size() ;i++){
                            if (BricksBreaker.oigArrayList.get(i) instanceof Brick){
                                BricksBreaker.oigArrayList.get(i).setVisible(brickVisibility);
                            }
                            if (BricksBreaker.oigArrayList.get(i) instanceof Item){
                                BricksBreaker.oigArrayList.get(i).setVisible(itemVisibility);
                            }
                            if (BricksBreaker.oigArrayList.get(i) instanceof Ball){
                                BricksBreaker.oigArrayList.get(i).setVisible(ballVisibility);
                            }
                        }
                    }

                    ballColorT.add();
                    brickColorT.add();
                    backgroundColorT.add();
                    ballItemColorT.add();
                    speedItemColorT.add();
                    powerItemColorT.add();

                    BricksBreaker.ballColor = new Color(ballColorT.getR() ,ballColorT.getG() ,ballColorT.getB());
                    BricksBreaker.brickColor = new Color(brickColorT.getR() ,brickColorT.getG() ,brickColorT.getB());
                    BricksBreaker.backgroundColor = new Color(backgroundColorT.getR() ,backgroundColorT.getG() ,backgroundColorT.getB());
                    BricksBreaker.ballItemColor = new Color(ballItemColorT.getR() ,ballItemColorT.getG() ,ballItemColorT.getB());
                    BricksBreaker.speedItemColor = new Color(speedItemColorT.getR() ,speedItemColorT.getG() ,speedItemColorT.getB());
                    BricksBreaker.powerItemColor = new Color(powerItemColorT.getR() ,powerItemColorT.getG() ,powerItemColorT.getB());
                }
                else {
                    reset();
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Default",Font.BOLD ,20));
        g.drawString("DANCE" ,(int) (brick.getX() + BricksBreaker.brickWidth / 5) ,(int) (brick.getY() + BricksBreaker.brickHeight / 4 * 3));
    }

    void reset(){
        time = PT.time;
        ballColorT = new MyColor(255 ,255 ,255);
        brickColorT = new MyColor(255 ,255 ,255);
        backgroundColorT = new MyColor(0 ,0 ,0);
        ballItemColorT = new MyColor(0 ,255 ,0);
        speedItemColorT = new MyColor(255 ,0 ,0);
        powerItemColorT = new MyColor(0 ,0 ,255);

        BricksBreaker.ballColor = Color.WHITE;
        BricksBreaker.brickColor = Color.WHITE;
        BricksBreaker.backgroundColor = Color.BLACK;
        BricksBreaker.ballItemColor = Color.GREEN;
        BricksBreaker.speedItemColor = Color.RED;
        BricksBreaker.powerItemColor = Color.BLUE;

        for (int i = 0; i < BricksBreaker.oigArrayList.size() ;i++){
            BricksBreaker.oigArrayList.get(i).setVisible(true);
        }
        brickVisibility = false;
        ballVisibility = true;
        itemVisibility = false;
    }
}
