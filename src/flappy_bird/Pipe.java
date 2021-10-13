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
    int x = GameManager.width + 50;
    int y = rand.nextInt((GameManager.height - 100) - 150) + 150;
    int size = 300;
    boolean isAlive;
    boolean createdAnother;
    Image topImage;
    Image bottomImage;
    
    public Pipe(GameManager gamePanel)
    {
        this.gamePanel = gamePanel;
        isAlive = true;
        createdAnother = false;
        topImage = (new ImageIcon("topPipe.png")).getImage();
        bottomImage = (new ImageIcon("bottomPipe.png")).getImage();
        start();
    }
    
    public void drawPipe(Graphics g)
    {
        g.drawImage(topImage, x, y - 450 - 200, size, size + 200, null);
        g.drawImage(bottomImage, x, y, size, /*size*/ size + 200, null);
        System.out.println("drawing pipe");
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
            
            if(x < -200)
            {
                isAlive = false;
            }
            
//            if(x == GameManager.width / 2 && !createdAnother)
//            {
//                gamePanel.pipes.add(new Pipe(gamePanel));
//                createdAnother = true;
//            }
            
            gamePanel.repaint();
        }
    }
}
