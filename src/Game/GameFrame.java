package Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        this.setSize(Game.GAME_WIDTH + 18,Game.GAME_HEIGHT + 47);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
