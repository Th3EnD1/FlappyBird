/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import flappy_bird.GameManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import client.ClientGamePanel;

public class GameFrame extends JFrame {
    public JFrame f;
    JPanel panel;
    JPanel container;

    int frameNumber;

    public GameFrame() {
        this.setTitle("Flappy Bird By Eden");
        panel = new Menu(this);
        panel.setBounds(0, 0, Menu.width, Menu.height);
        container = new JPanel();
        container.setLayout(null);
        container.setSize(Menu.width, Menu.height);
        container.add(panel);
        // this.setPreferredSize(new Dimension(Menu.width, Menu.height));
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

        switch (frameNumber) {
            case 1: {
                container.removeAll();
                panel = new GameManager(false);

                panel.setBounds(0, 0, GameManager.width, GameManager.height);
                container.setSize(GameManager.width, GameManager.height);
                container.add(panel);
                this.setSize(GameManager.width, GameManager.height);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setLocation(200, 200);
                this.setVisible(true);
                this.setFocusable(false);
            }
                break;

            case 2: {
                // ClientGamePanel run = new ClientGamePanel();
                container.removeAll();
                panel = new GameManager(true);

                panel.setBounds(0, 0, GameManager.width, GameManager.height);
                container.setSize(GameManager.width, GameManager.height);
                container.add(panel);
                this.setSize(GameManager.width + 512, GameManager.height);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setLocation(200, 200);
                this.setVisible(true);
                this.setFocusable(false);
            }
        }
    }
}
