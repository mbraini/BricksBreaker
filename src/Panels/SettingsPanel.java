package Panels;

import javax.swing.*;

public class SettingsPanel extends JPanel {
    JButton aimingB;
    JButton songThemeB;
    JButton savingB;
    JButton mainMenu;
    JLabel aimingL;
    JLabel songThemeL;
    JLabel savingL;
    public SettingsPanel(){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setVisible(false);
        initAiming();
        initSongTheme();
        initSaving();
        initMainMenu();
        setActionListeners();
    }

    private void setActionListeners() {
        aimingB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.Aiming = !Game.Aiming;
                if (Game.Aiming){
                    aimingB.setText("ON");
                }
                else {
                    aimingB.setText("OFF");
                }
            }
        });

        songThemeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.SongTheme = !Game.SongTheme;
                if (Game.SongTheme){
                    songThemeB.setText("ON");
                }
                else {
                    songThemeB.setText("OFF");
                }
            }
        });

        savingB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.Saving = !Game.Saving;
                if (Game.Saving){
                    savingB.setText("ON");
                }
                else {
                    savingB.setText("OFF");
                }
            }
        });

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.settingsPanel.setVisible(false);
                Game.mainPanel.setVisible(true);
            }
        });
    }

    private void initMainMenu() {
        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(Game.GAME_WIDTH/5  ,Game.GAME_HEIGHT/9 * 7 ,Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9);
        mainMenu.setOpaque(true);
        mainMenu.setBackground(Color.WHITE);
        mainMenu.setHorizontalTextPosition(JLabel.CENTER);
        mainMenu.setVerticalTextPosition(JLabel.CENTER);
        mainMenu.setFocusable(false);
        this.add(mainMenu);
    }

    void initAiming(){
        aimingL = new JLabel("Aiming :");
        aimingL.setBounds(Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        aimingL.setOpaque(true);
        aimingL.setBackground(Color.WHITE);
        aimingL.setHorizontalTextPosition(JLabel.CENTER);
        aimingL.setVerticalTextPosition(JLabel.CENTER);
        this.add(aimingL);

        String value;
        aimingB = new JButton();
        aimingB.setBounds(Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        aimingB.setOpaque(true);
        aimingB.setBackground(Color.WHITE);
        aimingB.setHorizontalTextPosition(JLabel.CENTER);
        aimingB.setVerticalTextPosition(JLabel.CENTER);
        aimingB.setFocusable(false);
        if (Game.Aiming){
            value = "ON";
        }
        else {
            value = "OFF";
        }
        aimingB.setText(value);
        this.add(aimingB);
    }

    void initSongTheme(){
        songThemeL = new JLabel("Song Theme :");
        songThemeL.setBounds(Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9 * 3 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        songThemeL.setOpaque(true);
        songThemeL.setBackground(Color.WHITE);
        songThemeL.setHorizontalTextPosition(JLabel.CENTER);
        songThemeL.setVerticalTextPosition(JLabel.CENTER);
        this.add(songThemeL);

        String value;
        songThemeB = new JButton();
        songThemeB.setBounds(Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9 * 3 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        songThemeB.setOpaque(true);
        songThemeB.setBackground(Color.WHITE);
        songThemeB.setHorizontalTextPosition(JLabel.CENTER);
        songThemeB.setVerticalTextPosition(JLabel.CENTER);
        songThemeB.setFocusable(false);
        if (Game.SongTheme){
            value = "ON";
        }
        else {
            value = "OFF";
        }
        songThemeB.setText(value);
        this.add(songThemeB);
    }

    void initSaving(){
        savingL = new JLabel("Saving :");
        savingL.setBounds(Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9 * 5 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        savingL.setOpaque(true);
        savingL.setBackground(Color.WHITE);
        savingL.setHorizontalTextPosition(JLabel.CENTER);
        savingL.setVerticalTextPosition(JLabel.CENTER);
        this.add(savingL);

        String value;
        savingB = new JButton();
        savingB.setBounds(Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9 * 5 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        savingB.setOpaque(true);
        savingB.setBackground(Color.WHITE);
        savingB.setHorizontalTextPosition(JLabel.CENTER);
        savingB.setVerticalTextPosition(JLabel.CENTER);
        savingB.setFocusable(false);
        if (Game.Saving){
            value = "ON";
        }
        else {
            value = "OFF";
        }
        savingB.setText(value);
        this.add(savingB);
    }

}
