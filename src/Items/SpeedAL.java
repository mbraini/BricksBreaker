package Items;

import Panels.BricksBreaker;
import Panels.PT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedAL implements ActionListener {

    Timer ability;

    SpeedAL(Timer ability){
        this.ability = ability;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (BricksBreaker.isRunning && PT.time - SpeedItem.time > 15000) {
            double V;
            BricksBreaker.ballVelocity = 6;
            for (int i = 0; i < BricksBreaker.oigArrayList.size(); i++) {
                if (BricksBreaker.oigArrayList.get(i) instanceof Ball) {
                    Ball ball = (Ball) BricksBreaker.oigArrayList.get(i);
                    V = Math.sqrt(Math.pow(ball.getxVelocity(), 2) + Math.pow(ball.getyVelocity(), 2));
                    ball.setxVelocity(ball.getxVelocity() * (6d / V));
                    ball.setyVelocity(ball.getyVelocity() * (6d / V));
                }
            }
            ability.stop();
        }
    }
}
