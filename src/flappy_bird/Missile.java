/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappy_bird;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Eden
 */
public class Missile extends Thread
{
    GameManager gamePanel;
    
    int x = GameManager.width + 500;
    int y = gamePanel.bird.y - ((gamePanel.bird.height) / 2);
    int imageWidth, imageHeight;
    
    boolean isAlive;
    Image warningImage;
    Image missileImage;
    
    public Missile()
    {
        warningImage = (new ImageIcon("warningImage.png")).getImage();
        missileImage = (new ImageIcon("missileImage.png")).getImage();
        imageWidth = warningImage.getWidth(null);
        imageHeight = warningImage.getHeight(null);
        start();
    }
    
    public void missileWarning(Graphics g)
    {
        if(isAlive && x >= GameManager.width)
        {
            g.drawImage(warningImage, GameManager.width - imageWidth - 5, y, imageWidth, imageHeight, null);
        }
    }
    
    public void drawMissile(Graphics g)
    {
        g.drawImage(missileImage, x, y, imageWidth, imageHeight, null);
    }
    
    public void run()
    {
        Graphics g = gamePanel.getGraphics();
        while(isAlive)
        {
            try
            {
                Thread.sleep(20);
            }
            catch(InterruptedException e) {}
            
            missileWarning(g);
            
            x -= 10;
            
            if (gamePanel.bird.y > y)
            {
                y++;
            }
            else
            {
                y--;
            }
            
            if(x < -200)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
}
