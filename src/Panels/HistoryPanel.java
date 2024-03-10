package Panels;

import javax.swing.*;
import Game.*;

public class HistoryPanel extends JPanel {
    public HistoryPanel(){
        this.setOpaque(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setVisible(false);
    }
}
