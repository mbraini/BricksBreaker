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
    }


    public void start() {
        bricksBreaker = new BricksBreaker();
        pt = new PT();
        this.setVisible(true);
        this.add(bricksBreaker);
        this.add(pt);
        bricksBreaker.start();
    }
}