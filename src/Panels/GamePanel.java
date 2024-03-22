package Panels;
import Game.*;
import Items.OIG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public static BricksBreaker bricksBreaker;
    public static PT pt;

    public GamePanel(){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        this.setOpaque(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        bricksBreaker = new BricksBreaker();
        pt = new PT();
        this.add(bricksBreaker);
        this.add(pt);
    }


    public void start() {
        this.remove(bricksBreaker);
        this.remove(pt);
        bricksBreaker = new BricksBreaker();
        pt = new PT();
        this.add(bricksBreaker);
        this.add(pt);
        this.setVisible(true);
        bricksBreaker.start();
    }

    static public void stop(){
        bricksBreaker.setVisible(false);
        bricksBreaker.stop();
        Game.gamePanel.setVisible(false);
        Game.endGamePanel.start(pt.point);
        pt.stop();
    }
}