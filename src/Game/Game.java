package Game;
import Panels.*;

import javax.swing.*;

public class Game {

    static public boolean Aiming = true;
    static public boolean SongTheme = true;
    static public boolean Saving = true;
    public static final int GAME_HEIGHT = 800;
    public static final int GAME_WIDTH = 600;
    public GameFrame gameFrame;
    static public MainPanel mainPanel;
    static public GamePanel gamePanel;
    static public HistoryPanel historyPanel;
    static public SettingsPanel settingsPanel;
    static public GamePrepPanel gamePrepPanel;
    static public JPanel panel;

    public Game() {
        gameFrame = new GameFrame();
        panel = new JPanel();
        panel.setLayout(null);

        mainPanel = new MainPanel();
        gamePanel = new GamePanel();
        historyPanel = new HistoryPanel();
        settingsPanel = new SettingsPanel();
        gamePrepPanel = new GamePrepPanel();
        gameFrame.setContentPane(panel);

        panel.add(mainPanel);
        panel.add(historyPanel);
        panel.add(settingsPanel);
        panel.add(gamePrepPanel);
        panel.add(gamePanel);


        gameFrame.setVisible(true);
    }
}
