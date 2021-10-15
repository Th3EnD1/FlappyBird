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
    int width = size + 15;
    int height = size;
    Image birdImage;
    int yMotion;
    int ticks;
    //Rectangle birdRec;
    
    public int midX, midY;
    
    public Bird(GameManager gamePanel)
    {
        midX = (x + width) / 2;
        midY = (y + height) / 2;
        this.gamePanel = gamePanel;
        ImageIcon img = new ImageIcon("birdImage.png");
        birdImage = img.getImage();
        //birdRec = new Rectangle(x, y, size + 15, size);
        start();
    }
    
    public void drawBird(Graphics g)
    {
        g.drawImage(birdImage, x, y, width, height, null);
    }
    
    public void resetMotion()
    {
        if(yMotion > 0)
        {
            yMotion = 0;
        }
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
            
            if(gamePanel.gameActive)
            {
                if(ticks % 2 == 0 && yMotion < 10)
                {
                    yMotion += 2;
                }
                y += yMotion;
                //birdRec.y += yMotion;

                if(y + yMotion >= GameManager.height - 125)
                {
                    y = GameManager.height - 115;
                    //birdRec.y = GameManager.height - 115;
                }
            }
            else
            {
                if(ticks % 2 == 0 && yMotion < 10)
                {
                    yMotion += 2;
                }
                y += yMotion;
                
                if(y + yMotion >= GameManager.height - 125)
                {
                    y = GameManager.height - 115;
                    //birdRec.y = GameManager.height - 115;
                }
            }
            gamePanel.repaint();
        }
    }
}
