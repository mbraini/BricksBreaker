package Panels;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class PT extends JPanel {

    public PT(){
        this.setLayout(null);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT / 10);
        this.setBackground(Color.YELLOW);
    }
}
