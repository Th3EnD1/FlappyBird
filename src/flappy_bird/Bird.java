/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import client.ClientGamePanel;
import static client.ClientGamePanel.height;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Eden
 */
public class Bird extends Thread
{
    JPanel gamePanel;
    int scrennWidth = 1200;
    int screenHeight = 700;
    public int x = (scrennWidth / 2) - 10;
    public int y = (screenHeight / 2) - 10;
    public int width = 50;
    public int height = 50;
    Image birdImage;
    public int yMotion;
    int ticks;
    public boolean isAlive;
    public int midX, midY;
    
    public Bird(JPanel gamePanel)
    {
        this.gamePanel = gamePanel;
        midX = (x + width) / 2;
        midY = (y + height) / 2;
        isAlive = false;
        ImageIcon img = new ImageIcon("laBird.png");
        birdImage = img.getImage();
        birdImage = birdImage.getScaledInstance(50, 35, Image.SCALE_DEFAULT);
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
            
            if(isAlive)
            {
                if(ticks % 2 == 0 && yMotion < 10)
                {
                    yMotion += 2;
                }
                y += yMotion;
                //birdRec.y += yMotion;

                if(y + yMotion >= screenHeight - 125)
                {
                    y = screenHeight - 115;
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
                
                if(y + yMotion >= screenHeight - 125)
                {
                    y = screenHeight - 115;
                    //birdRec.y = GameManager.height - 115;
                }
            }
            gamePanel.repaint();
        }
    }
}
