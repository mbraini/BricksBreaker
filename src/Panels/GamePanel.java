package Panels;
import Game.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    static public Color ballColor;
    static public String username;
    static public String difficulty;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }
}
