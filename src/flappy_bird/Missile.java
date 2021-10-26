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
    
    int x = GameManager.width + 1500;
    int y = GameManager.height / 2/*gamePanel.bird.y - ((gamePanel.bird.height) / 2)*/;
    int imageWidth, imageHeight;
    long Spawntime, currTime;
    
    boolean isAlive;
    Image warningImage;
    Image missileImage;
    
    public Missile(GameManager gamePanel)
    {
        isAlive = true;
        Spawntime = System.currentTimeMillis();
        currTime = 0;
        this.gamePanel = gamePanel;
        warningImage = (new ImageIcon("warningImage.png")).getImage();
        ImageIcon img = new ImageIcon("missileImage.png");
        missileImage = img.getImage();
        imageWidth = 80;
        imageHeight = 80;
        missileImage = missileImage.getScaledInstance(56, 50, Image.SCALE_DEFAULT);
        start();
    }
    
    public void drawWarning(Graphics g)
    {
        if(isAlive && x >= GameManager.width)
        {
            g.drawImage(warningImage, GameManager.width - imageWidth - 5, y, 80, 80, null);
        }
    }
    
    public void drawMissile(Graphics g)
    {
        g.drawImage(missileImage, x, y, 70, 60, null);
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
            
            //drawWarning(g);
//            currTime = System.currentTimeMillis();
//            if(isAlive && x >= GameManager.width && (currTime - Spawntime <= 3000))
//            {
//                g.drawImage(warningImage, GameManager.width - imageWidth - 5, y, imageWidth, imageHeight, null);
//            }
            
            x -= 10;
            
            if(x >= gamePanel.bird.x)
            {
                if(gamePanel.bird.y > y)
                {
                    y = y + 2;
                }
                else
                {
                    y = y - 2;
                }
            }
            
            if(x < -200)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
}
