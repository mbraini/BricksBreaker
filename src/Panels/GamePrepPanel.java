package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Game.*;
import Items.DanceLightItem;

public class GamePrepPanel extends JPanel {

    JButton difficultyBE;
    JButton difficultyBM;
    JButton difficultyBH;
    JButton ballColorB;
    JButton userNameB;
    JTextField userNameT;
    JButton startGame;
    JLabel difficultyL;
    JLabel ballColorL;
    JLabel userNameL;
    public GamePrepPanel(){
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        initDifficulty();
        initBallColor();
        initUserName();
        initStartGame();
        setActionListeners();
    }

    private void initUserName() {
        userNameL = new JLabel("Username :");
        userNameL.setBounds(Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9 * 5 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        userNameL.setOpaque(true);
        userNameL.setBackground(Color.WHITE);
        userNameL.setHorizontalAlignment(JLabel.CENTER);
        userNameL.setVerticalAlignment(JLabel.CENTER);
        this.add(userNameL);

        userNameB = new JButton("Submit");
        userNameB.setBounds(Game.GAME_WIDTH/7 * 5 ,Game.GAME_HEIGHT/9 * 5 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        userNameB.setOpaque(true);
        userNameB.setBackground(Color.WHITE);
        userNameB.setHorizontalTextPosition(JLabel.CENTER);
        userNameB.setVerticalTextPosition(JLabel.CENTER);
        userNameB.setFocusable(false);
        this.add(userNameB);

        userNameT = new JTextField();
        userNameT.setBounds(Game.GAME_WIDTH/7 * 3 ,Game.GAME_HEIGHT/9 * 5 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        this.add(userNameT);
    }

    private void setActionListeners() {

        ballColorB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Pick The Ball Color", Color.WHITE);
                BricksBreaker.ballColor = color;
                ballColorL.setBackground(color);
                ballColorB.setBackground(color);
                DanceLightItem.ballColor = color;
            }
        });

        userNameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userNameT.getText();
                if (text.length() != 0) {
                    BricksBreaker.username = text;
                    userNameT.setFocusable(false);
                }
            }
        });

        difficultyBE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyBE.setBackground(Color.GRAY);
                difficultyBM.setBackground(Color.WHITE);
                difficultyBH.setBackground(Color.WHITE);
                BricksBreaker.difficulty = "Easy";
            }
        });

        difficultyBM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyBE.setBackground(Color.WHITE);
                difficultyBM.setBackground(Color.GRAY);
                difficultyBH.setBackground(Color.WHITE);
                BricksBreaker.difficulty = "Medium";
            }
        });

        difficultyBH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyBE.setBackground(Color.WHITE);
                difficultyBM.setBackground(Color.WHITE);
                difficultyBH.setBackground(Color.GRAY);
                BricksBreaker.difficulty = "Hard";
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameT.getText().length() != 0){
                    Game.playerName = userNameT.getText();
                    while (Game.playerName.contains(" ")){
                        String answer;
                        answer = Game.playerName.substring(0 ,Game.playerName.indexOf(" "));
                        if (Game.playerName.indexOf(" ") == Game.playerName.length() - 1){
                            Game.playerName = answer;
                            break;
                        }
                        answer += Game.playerName.substring(Game.playerName.indexOf(" ") + 1);
                        Game.playerName = answer;
                    }
                    if (Game.playerName.equals("")){
                        Game.playerName = "BackSpace:)";
                    }
                    Game.gamePrepPanel.setVisible(false);
                    Game.gamePanel.start();
                }
            }
        });

    }

    private void initStartGame() {
        startGame = new JButton("Start Game");
        startGame.setBounds(Game.GAME_WIDTH/5  ,Game.GAME_HEIGHT/9 * 7 ,Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9);
        startGame.setOpaque(true);
        startGame.setBackground(Color.WHITE);
        startGame.setHorizontalTextPosition(JLabel.CENTER);
        startGame.setVerticalTextPosition(JLabel.CENTER);
        startGame.setFocusable(false);
        this.add(startGame);
    }


    void initBallColor(){
        ballColorL = new JLabel("Ball's Color :");
        ballColorL.setBounds(Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9 * 3 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        ballColorL.setOpaque(true);
        ballColorL.setBackground(Color.WHITE);
        ballColorL.setHorizontalAlignment(JLabel.CENTER);
        ballColorL.setVerticalAlignment(JLabel.CENTER);
        this.add(ballColorL);

        ballColorB = new JButton("Choose Color");
        ballColorB.setBounds(Game.GAME_WIDTH/5 * 3 ,Game.GAME_HEIGHT/9 * 3 ,Game.GAME_WIDTH/5 ,Game.GAME_HEIGHT/9);
        ballColorB.setOpaque(true);
        ballColorB.setBackground(Color.WHITE);
        ballColorB.setHorizontalTextPosition(JLabel.CENTER);
        ballColorB.setVerticalTextPosition(JLabel.CENTER);
        ballColorB.setFocusable(false);
        this.add(ballColorB);
    }

    void initDifficulty(){
        difficultyL = new JLabel("Difficulty :");
        difficultyL.setBounds(Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        difficultyL.setOpaque(true);
        difficultyL.setBackground(Color.WHITE);
        difficultyL.setHorizontalAlignment(JLabel.CENTER);
        difficultyL.setVerticalAlignment(JLabel.CENTER);
        this.add(difficultyL);


        difficultyBE = new JButton("Easy");
        difficultyBE.setBounds(Game.GAME_WIDTH/7 * 3 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        difficultyBE.setOpaque(true);
        difficultyBE.setBackground(Color.WHITE);
        difficultyBE.setHorizontalTextPosition(JLabel.CENTER);
        difficultyBE.setVerticalTextPosition(JLabel.CENTER);
        difficultyBE.setFocusable(false);
        this.add(difficultyBE);


        difficultyBM = new JButton("Medium");
        difficultyBM.setBounds(Game.GAME_WIDTH/7 * 4 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        difficultyBM.setOpaque(true);
        difficultyBM.setBackground(Color.GRAY);
        difficultyBM.setHorizontalTextPosition(JLabel.CENTER);
        difficultyBM.setVerticalTextPosition(JLabel.CENTER);
        difficultyBM.setFocusable(false);
        this.add(difficultyBM);

        difficultyBH = new JButton("Hard");
        difficultyBH.setBounds(Game.GAME_WIDTH/7 * 5 ,Game.GAME_HEIGHT/9 ,Game.GAME_WIDTH/7 ,Game.GAME_HEIGHT/9);
        difficultyBH.setOpaque(true);
        difficultyBH.setBackground(Color.WHITE);
        difficultyBH.setHorizontalTextPosition(JLabel.CENTER);
        difficultyBH.setVerticalTextPosition(JLabel.CENTER);
        difficultyBH.setFocusable(false);
        this.add(difficultyBH);

    }

    public void start() {
        this.setVisible(true);
        this.remove(difficultyBE);
        this.remove(difficultyBM);
        this.remove(difficultyBH);
        this.remove(ballColorB);
        this.remove(userNameB);
        this.remove(userNameT);
        this.remove(startGame);
        this.remove(difficultyL);
        this.remove(ballColorL);
        this.remove(userNameL);

        initDifficulty();
        initBallColor();
        initUserName();
        initStartGame();
        setActionListeners();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font(null ,Font.BOLD ,20));
        g.setColor(Color.GREEN);
        g.fillOval(10 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28);
        g.drawString(" : New Ball" ,Game.GAME_HEIGHT / 28 + 10 ,Game.GAME_HEIGHT / 28 * 7 / 4);
        g.setColor(Color.RED);
        g.fillOval(Game.GAME_HEIGHT / 28 * 5 + 20 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28);
        g.drawString(" : Speed" ,Game.GAME_HEIGHT / 28 * 6 + 20 ,Game.GAME_HEIGHT / 28 * 7 / 4);
        g.setColor(Color.BLUE);
        g.fillOval(Game.GAME_HEIGHT / 28 * 10 + 10 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28);
        g.drawString(" : Extra Damage" ,Game.GAME_HEIGHT / 28 * 11 + 10 ,Game.GAME_HEIGHT / 28 * 7 / 4);
        g.setColor(Color.YELLOW);
        g.fillOval(Game.GAME_HEIGHT / 28 * 17 + 10 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28);
        g.drawString(" : Dizzy" ,Game.GAME_HEIGHT / 28 * 18 + 10 ,Game.GAME_HEIGHT / 28 * 7 / 4);
        g.setColor(new Color(255 ,192 ,203));
        g.fillOval(Game.GAME_HEIGHT / 28 + 25 + 20 ,Game.GAME_HEIGHT / 28 * 26 + 8 ,Game.GAME_HEIGHT / 28 ,Game.GAME_HEIGHT / 28);
        g.drawString(" : Backwards" ,Game.GAME_HEIGHT / 28 * 3 + 20 ,Game.GAME_HEIGHT / 28 * 27);
        g.setColor(Color.MAGENTA);
        g.drawString("DANCE" ,Game.GAME_HEIGHT / 28 * 10 + 20 ,Game.GAME_HEIGHT / 28 * 27);
        g.drawString("QUAKE" ,Game.GAME_HEIGHT / 28 * 15 + 20 ,Game.GAME_HEIGHT / 28 * 27);
    }
}