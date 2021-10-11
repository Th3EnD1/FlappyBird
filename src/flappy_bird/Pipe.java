/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author student
 */
public class Pipe extends Thread
{
    GameManager gamePanel;
    Random rand = new Random();
    int x = GameManager.width;
    int y = 0;
    int size = 300;
    boolean isAlive;
    Image topImage;
    Image bottomImage;
    
    public Pipe(GameManager gamePanel)
    {
        this.gamePanel = gamePanel;
        isAlive = true;
        topImage = (new ImageIcon("topPipe.png")).getImage();
        bottomImage = (new ImageIcon("bottomPipe.png")).getImage();
        start();
    }
    
    public void drawPipe(Graphics g)
    {
        g.drawImage(bottomImage, x, y + 500, size, size, null);
        g.drawImage(topImage, x, y, size, size, null);
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e) {}
            
            x -= 10;
            
            if(x < -50)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
}
