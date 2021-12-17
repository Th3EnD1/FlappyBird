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
 * @author Eden
 */
public class Pipe extends Thread
{
    GameManager gamePanel;
    Random rand = new Random();
    public int x = GameManager.width + 50;
    int bottomHeight;
    public int imageWidth, imageHeight;//dimesions of images
    int space = 200;
    int spaceFromTopWall = 50;
    int spaceFromBottomWall = 100;
    
    boolean isAlive;
    boolean frozen;
    public boolean scored;
    
    Image topImage;
    Image bottomImage;
    
    public int topPipeX, topPipeY;
    public int botPipeX, botPipeY;
    
    public int topMidX, topMidY;
    public int botMidX, botMidY;
    
    
    
    public Pipe(GameManager gamePanel)
    {
        updatePipeProperties();
        
        this.gamePanel = gamePanel;
        isAlive = true;
        frozen = false;
        scored = false;
        topImage = (new ImageIcon("topPipe.png")).getImage();
        bottomImage = (new ImageIcon("bottomPipe.png")).getImage();
        imageWidth = topImage.getWidth(null);
        imageHeight = topImage.getHeight(null);
        bottomHeight = (new Random()).nextInt(GameManager.height - space - spaceFromTopWall - spaceFromBottomWall ) + space + spaceFromTopWall;
        //topRec = new Rectangle(x, bottomHeight - space - imageHeight, imageWidth, imageHeight);
        //bottomRec = new Rectangle(x, bottomHeight, imageWidth, imageHeight);
        start();
    }
    
    public void drawPipe(Graphics g)
    {
        g.drawImage(topImage, topPipeX, topPipeY, imageWidth, imageHeight, null);
        g.drawImage(bottomImage, botPipeX, botPipeY, imageWidth, imageHeight, null);
    }
    
    public void run()
    {
        while(isAlive)
        {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {}
            
            if (!frozen) 
            {
                x -= 10;
                updatePipeProperties();
            }
            
            if(x < -200)
            {
                isAlive = false;
            }
            
            gamePanel.repaint();
        }
    }
    
    public void updatePipeProperties()
    {
        topPipeX = x;
        topPipeY = bottomHeight - space - imageHeight;
        botPipeX = x;
        botPipeY = bottomHeight;
        
        topMidX = (topPipeX + imageWidth) / 2;
        topMidY = (topPipeY + imageHeight) / 2;
        botMidX = (botPipeX + imageWidth) / 2;
        botMidY = (botPipeY + imageHeight) / 2;
    }
}
