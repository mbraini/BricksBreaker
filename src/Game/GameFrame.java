package Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        this.setSize(Game.GAME_WIDTH + 14,Game.GAME_HEIGHT + 37);
        this.setResizable(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
