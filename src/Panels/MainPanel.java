package Panels;
import Game.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPanel extends JPanel {
    JButton newGame;
    JLabel record;
    JButton history;
    JButton settings;

    public MainPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        initNewGameButton();
        initRecordLabel();
        initHistoryButton();
        initSettingsButton();
        setActionListeners();
    }

    private void setActionListeners() {
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.mainPanel.setVisible(false);
                Game.gamePanel.setVisible(false);
                Game.gamePrepPanel.start();
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.mainPanel.setVisible(false);
                Game.historyPanel.start();
            }
        });

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.mainPanel.setVisible(false);
                Game.settingsPanel.setVisible(true);
            }
        });
    }

    void initNewGameButton(){
        newGame = new JButton();
        newGame.setBounds(0,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/4,Game.GAME_HEIGHT/6);
        newGame.setText("New Game");
        newGame.setBackground(Color.WHITE);
        newGame.setOpaque(true);
        newGame.setHorizontalTextPosition(JLabel.RIGHT);
        newGame.setVerticalTextPosition(JLabel.TOP);
        newGame.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        newGame.setFocusable(false);
        this.add(newGame);
    }

    void initRecordLabel(){
        record = new JLabel();
        record.setBounds(Game.GAME_WIDTH/4,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/4,Game.GAME_HEIGHT/6);
        record.setText("Your Record");
        record.setBackground(Color.WHITE);
        record.setOpaque(true);
        record.setHorizontalTextPosition(JLabel.RIGHT);
        record.setVerticalTextPosition(JLabel.TOP);
        record.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        this.add(record);
    }

    void initHistoryButton(){
        history = new JButton();
        history.setBounds(Game.GAME_WIDTH/4 * 2,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/4,Game.GAME_HEIGHT/6);
        history.setText("History");
        history.setBackground(Color.WHITE);
        history.setOpaque(true);
        history.setHorizontalTextPosition(JLabel.RIGHT);
        history.setVerticalTextPosition(JLabel.TOP);
        history.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        history.setFocusable(false);
        this.add(history);
    }

    void initSettingsButton(){
        settings = new JButton();
        settings.setBounds(Game.GAME_WIDTH/4 * 3,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/4,Game.GAME_HEIGHT/6);
        settings.setText("Settings");
        settings.setBackground(Color.WHITE);
        settings.setOpaque(true);
        settings.setHorizontalTextPosition(JLabel.RIGHT);
        settings.setVerticalTextPosition(JLabel.TOP);
        settings.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        settings.setFocusable(false);
        this.add(settings);
    }

    public void start() {
        this.setVisible(true);
        FileInputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileInputStream("src/Game/History.txt");
            ArrayList<Integer> points = new ArrayList<>();
            Scanner scanner = new Scanner(fileOutputStream);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                String name = string.substring(0 ,string.indexOf(" "));
                string = string.substring(string.indexOf(" ") + 1);

                String point = string.substring(0 ,string.indexOf(" "));
                points.add(Integer.valueOf(point));
            }
            if (points.isEmpty()){
                record.setText("You Don't Have A Record!");
            }
            else {
                record.setText("Your Record : " + GameHelper.Max(points));
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
