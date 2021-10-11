/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author student
 */
public class Bird extends Thread
{
    GameManager gamePanel;
    int x = GameManager.width / 2 - 10, y = GameManager.height / 2 - 10;
    int size = 35;
    Image birdImage;
    int yMotion;
    int ticks;
    
    public Bird(GameManager gamePanel)
    {
        this.gamePanel = gamePanel;
        ImageIcon img = new ImageIcon("birdImage.png");
        birdImage = img.getImage();
        start();
    }
    
    public void drawBird(Graphics g)
    {
        g.drawImage(birdImage, x, y, size + 15, size, null);
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(15);
            }
            catch (InterruptedException e) {}
            
            ticks++;
            if(ticks % 2 == 0 && yMotion < 15)
            {
                yMotion += 2;
            }
            y += yMotion;
            
            if(y + yMotion >= GameManager.height - 130)
            {
                y = GameManager.height - 115;
            }
            
            gamePanel.repaint();
        }
    }
}
