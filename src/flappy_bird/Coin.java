/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import server.ServerGameManager;

/**
 *
 * @author Eden
 */
public class Coin extends Thread
{
    GameManager gamePanel;
    long spawnTime;
    boolean isAlive;
    boolean spawned;
    public int y, x = GameManager.width + 50;
    public int r = 15;
    
    public Coin(GameManager gamePanel, int height, long spawnTime)
    {
        isAlive = true;
        spawned = false;
        this.spawnTime = spawnTime;
        y = height;
        this.gamePanel = gamePanel;
        start();
    }
    
    public void run()
    {
        try
        {
            Thread.sleep(spawnTime);
        }
        catch (InterruptedException e) {}
        while(isAlive)
        {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {}
            
            x -= 10;
            
            if(x < -200)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
    public void drawCoin(Graphics g)
    {
        g.drawImage(GameManager.coinImage, x, y, r*2, r*2, null);
    }
}
