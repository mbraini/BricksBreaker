package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Game.*;

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
        userNameL.setHorizontalTextPosition(JLabel.CENTER);
        userNameL.setVerticalTextPosition(JLabel.CENTER);
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
                GamePanel.ballColor = color;
                ballColorL.setBackground(color);
                ballColorB.setBackground(color);
            }
        });

        userNameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = userNameT.getText();
                if (text.length() != 0) {
                    GamePanel.username = text;
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
                GamePanel.difficulty = "Easy";
            }
        });

        difficultyBM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyBE.setBackground(Color.WHITE);
                difficultyBM.setBackground(Color.GRAY);
                difficultyBH.setBackground(Color.WHITE);
                GamePanel.difficulty = "Medium";
            }
        });

        difficultyBH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyBE.setBackground(Color.WHITE);
                difficultyBM.setBackground(Color.WHITE);
                difficultyBH.setBackground(Color.GRAY);
                GamePanel.difficulty = "Hard";
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameT.getText().length() != 0){
                    Game.gamePrepPanel.setVisible(false);
                    Game.gamePanel.setVisible(true);
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
        ballColorL.setHorizontalTextPosition(JLabel.CENTER);
        ballColorL.setVerticalTextPosition(JLabel.CENTER);
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
        difficultyL.setHorizontalTextPosition(JLabel.CENTER);
        difficultyL.setVerticalTextPosition(JLabel.CENTER);
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

}
