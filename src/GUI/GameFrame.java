/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import flappy_bird.GameManager;
import server.InitServer;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import client.ClientGamePanel;

import client.EnemyPanel;

public class GameFrame extends JFrame {
    public JFrame f;
    JPanel panel;
    Menu menuPanel;
    JPanel container;
    EnemyPanel enemyPanel;

    int port = 25565;
    InitServer server = new InitServer(port);

    int frameNumber;

    public GameFrame() {
        panel = new Menu(this);
        panel.setBounds(0, 0, Menu.width, Menu.height);

        container = new JPanel();
        container.setLayout(null);
        container.setSize(Menu.width, Menu.height);
        container.add(panel);

        this.setTitle("Flappy Bird By Eden");
        this.setLayout(null);
        this.setSize(Menu.width, Menu.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(200, 200);
        this.setVisible(true);
        this.setFocusable(false);
        this.add(container);
    }

    public void updateFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        container.removeAll();

        switch (frameNumber) {
            case 1: {
                panel = new GameManager(false);
                panel.setBounds(0, 0, GameManager.width, GameManager.height);
                container.setSize(GameManager.width, GameManager.height);
                container.add(panel);
                this.setSize(GameManager.width, GameManager.height);
            }
                break;

            case 2: {
                panel = new GameManager(true);

                panel.setBounds(0, 0, GameManager.width, GameManager.height);
                container.setSize(GameManager.width, GameManager.height);
                container.add(panel);

                enemyPanel = new EnemyPanel((GameManager) panel);
                enemyPanel.setBounds(GameManager.width, 0, 512, GameManager.height);
                this.add(enemyPanel);
                this.setSize(GameManager.width + 512, GameManager.height);

            }
                break;
        }
    }
}
