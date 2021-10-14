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
    int bottomHeight;
    int imageWidth = 300, imageHeight = 500;//dimesions of images
    int space = 200;
    int spaceFromTopWall = 50;
    int spaceFromBottomWall = 100;
    boolean isAlive;
    boolean createdAnother;
    Image topImage;
    Image bottomImage;
    Rectangle topRec;
    Rectangle bottomRec;
    
    public Pipe(GameManager gamePanel)
    {
        this.gamePanel = gamePanel;
        isAlive = true;
        createdAnother = false;
        topImage = (new ImageIcon("topPipe.png")).getImage();
        bottomImage = (new ImageIcon("bottomPipe.png")).getImage();
        bottomHeight = (new Random()).nextInt(GameManager.height - space - spaceFromTopWall - spaceFromBottomWall ) + space + spaceFromTopWall;
        topRec = new Rectangle(x, bottomHeight - space - imageHeight, imageWidth, imageHeight);
        bottomRec = new Rectangle(x, bottomHeight, imageWidth, imageHeight);
        start();
    }
    
    public void drawPipe(Graphics g)
    {
        g.drawImage(topImage, x, bottomHeight - space - imageHeight, imageWidth, imageHeight, null);
        g.drawImage(bottomImage, x, bottomHeight, imageWidth, imageHeight, null);
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {}
            
            x -= 10;
            topRec.x -= 10;
            bottomRec.x -= 10;
            
            if(x < -200)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
}
