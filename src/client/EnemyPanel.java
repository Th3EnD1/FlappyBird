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
    Image background;

    public EnemyPanel(GameManager game) {
        background = (new ImageIcon("InfoBackground.jpg")).getImage();
        this.game = game;
        width = 512;
        height = 350;

        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 25));
        
        if (!game.oppDead) 
        {
            g.drawString("Opponent is still alive", (height / 2) - 50, height / 2);
        }
        else
        {
            if (game.score > game.oppScore) 
            {
                g.drawString("You Won!!!", (height / 2) - 50, 200);
            }
            else
            {
                g.drawString("You Lost :(", (height / 2) - 50, 200);
            }
            g.drawString("Opponent's Score: " + game.oppScore, (height / 2) - 50, 150);
        }
        

        repaint();
    }
}
