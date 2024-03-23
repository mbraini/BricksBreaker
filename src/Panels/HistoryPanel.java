package Panels;

import javax.swing.*;
import Game.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class HistoryPanel extends JPanel {
    JScrollPane jScrollPane;
    Container container;
    JButton menu;

    public HistoryPanel(){
        this.setOpaque(false);
        this.setBounds(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setVisible(false);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        container = new Container();
        container.setLayout(new GridLayout(1000 ,4 ,10 ,50));

        jScrollPane = new JScrollPane(container ,JScrollPane.VERTICAL_SCROLLBAR_NEVER ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        initMenu();
        this.add(jScrollPane);
    }

    private void initMenu() {
        menu = new JButton();
        menu.setBounds(Game.GAME_WIDTH / 3,Game.GAME_HEIGHT * 5 /6 ,Game.GAME_WIDTH/3,Game.GAME_HEIGHT/6);
        menu.setText("Menu");
        menu.setBackground(Color.WHITE);
        menu.setOpaque(true);
        menu.setHorizontalTextPosition(JButton.CENTER);
        menu.setVerticalTextPosition(JButton.CENTER);
        menu.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        menu.setFocusable(false);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.historyPanel.setVisible(false);
                Game.mainPanel.start();
            }
        });

        this.add(menu);
    }

    public void start(){
        this.setVisible(true);
        this.remove(jScrollPane);
        container = new Container();
        container.setLayout(new GridLayout(1000 ,1 ,10 ,50));
        container.setBounds(0 ,0 ,Game.GAME_WIDTH ,5000);
        container.setBackground(Color.BLACK);
        try {
            FileInputStream fileOutputStream = new FileInputStream("src/Game/History.txt");
            Scanner scanner = new Scanner(fileOutputStream);
            while (scanner.hasNextLine()){
                String string = scanner.nextLine();
                String name = string.substring(0 ,string.indexOf(" "));
                string = string.substring(string.indexOf(" ") + 1);

                String point = string.substring(0 ,string.indexOf(" "));
                string = string.substring(string.indexOf(" ") + 1);

                String date = string;

                JLabel label = new JLabel("name : " + name + "  point : " + point + "  date and time : " + date);
                label.setFont(new Font(null ,Font.BOLD ,20));
                label.setOpaque(true);
                label.setBackground(Color.CYAN);
                container.add(label);
            }
        }
        catch (Exception e){
            System.out.println("File Not Found");
        }
        jScrollPane = new JScrollPane(container ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(0 ,0 ,Game.GAME_WIDTH ,Game.GAME_HEIGHT * 5 / 6);
        jScrollPane.setBackground(Color.BLACK);
        this.add(jScrollPane);
    }
}
