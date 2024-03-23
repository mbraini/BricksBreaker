package Panels;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class EndGamePanel extends JPanel {

    static JButton menu;
    static JButton playAgain;
    static JButton prepGame;
    static JLabel point;

    public EndGamePanel(){
        this.setLayout(null);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);
        InitiateMenu();
        InitiatePrepGame();
        InitiatePlayAgain();
        InitiatePoint();

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.endGamePanel.setVisible(false);
                Game.mainPanel.setVisible(true);
            }
        });

        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.endGamePanel.setVisible(false);
                Game.gamePanel.start();
            }
        });

        prepGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.endGamePanel.setVisible(false);
                Game.gamePrepPanel.start();
            }
        });


    }

    private void InitiatePoint() {
        point = new JLabel();
        point.setBounds(Game.GAME_WIDTH/3,Game.GAME_HEIGHT * 4 / 6,Game.GAME_WIDTH / 3,Game.GAME_HEIGHT / 6);
        point.setText("Your Record");
        point.setBackground(Color.WHITE);
        point.setOpaque(true);
        point.setFont(new Font(null,Font.BOLD ,10));
        point.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        point.setHorizontalAlignment(JLabel.CENTER);
        point.setVerticalAlignment(JLabel.CENTER);
        this.add(point);
    }


    void InitiateMenu(){
        menu = new JButton();
        menu.setBounds(0,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/3,Game.GAME_HEIGHT/6);
        menu.setText("Menu");
        menu.setBackground(Color.WHITE);
        menu.setOpaque(true);
        menu.setHorizontalTextPosition(JButton.CENTER);
        menu.setVerticalTextPosition(JButton.CENTER);
        menu.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        menu.setFocusable(false);
        this.add(menu);
    }

    void InitiatePlayAgain(){
        playAgain = new JButton();
        playAgain.setBounds(Game.GAME_WIDTH / 3 ,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/3,Game.GAME_HEIGHT/6);
        playAgain.setText("Play Again");
        playAgain.setBackground(Color.WHITE);
        playAgain.setOpaque(true);
        playAgain.setHorizontalTextPosition(JButton.CENTER);
        playAgain.setVerticalTextPosition(JButton.CENTER);
        playAgain.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        playAgain.setFocusable(false);
        this.add(playAgain);
    }

    void InitiatePrepGame(){
        prepGame = new JButton();
        prepGame.setBounds(Game.GAME_WIDTH / 3 * 2 ,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/3,Game.GAME_HEIGHT/6);
        prepGame.setText("Play With New Settings");
        prepGame.setBackground(Color.WHITE);
        prepGame.setOpaque(true);
        prepGame.setHorizontalTextPosition(JButton.CENTER);
        prepGame.setVerticalTextPosition(JButton.CENTER);
        prepGame.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        prepGame.setFocusable(false);
        this.add(prepGame);
    }

    public void start(double point){
        this.setVisible(true);
        if (Game.Saving){
            save(point);
        }
        EndGamePanel.point.setText("Your Score : " + (int) point);
    }

    private void save(double point) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("src/Game/History.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()){
                strings.add(sc.nextLine());
            }
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            strings.add(Game.playerName + " " + (int) point + " " + currentDate.toString());
            PrintStream ps = new PrintStream("src/Game/History.txt");
            for (int i = 0 ;i < strings.size() ;i++){
                ps.println(strings.get(i));
            }
        }
        catch (Exception e){

        }
    }
}
