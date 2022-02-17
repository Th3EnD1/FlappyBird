package client;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import flappy_bird.GameManager;

public class EnemyPanel extends JPanel {
    GameManager game;
    int width;
    int height;
    public Image enemyScreen;

    public EnemyPanel(GameManager game) {
        this.game = game;
        width = 512;
        height = 350;

        setBackground(Color.BLACK);
        enemyScreen = (new ImageIcon("background.jpg")).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // // Duplicates the game screen into the mini screen
        // if (game.enemyData.player == game.player) {
        // enemyScreen = game.enemyData.screen.getImage();
        // }
        g.drawImage(enemyScreen, 0, height, width, height, null);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 25));
        g.drawString("Health: ?????", (height / 2) - 50, height / 5);

        repaint();
    }
}
