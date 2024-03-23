package Game;
import Panels.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game {

    static public boolean Aiming = true;
    static public boolean SongTheme = true;
    static public boolean Saving = true;
    static public String playerName;
    public static final int GAME_HEIGHT = 800;
    public static final int GAME_WIDTH = 600;
    public GameFrame gameFrame;
    static public MainPanel mainPanel;
    static public GamePanel gamePanel;
    static public HistoryPanel historyPanel;
    static public SettingsPanel settingsPanel;
    static public GamePrepPanel gamePrepPanel;
    static public JPanel panel;
    static public EndGamePanel endGamePanel;
    static public Image menu;
    static public Image gameOver;
    public Game() {

        gameFrame = new GameFrame();
        panel = new JPanel();
        panel.setLayout(null);

        mainPanel = new MainPanel();
        gamePanel = new GamePanel();
        historyPanel = new HistoryPanel();
        settingsPanel = new SettingsPanel();
        gamePrepPanel = new GamePrepPanel();
        endGamePanel = new EndGamePanel();
        gameFrame.setContentPane(panel);

        panel.add(mainPanel);
        panel.add(historyPanel);
        panel.add(settingsPanel);
        panel.add(gamePrepPanel);
        panel.add(gamePanel);
        panel.add(endGamePanel);

        try {
            menu = ImageIO.read(new File("src/Game/Menu.png"));
            gameOver = ImageIO.read(new File("src/Game/GameOver.png"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        gameFrame.setVisible(true);
        mainPanel.start();
    }
}