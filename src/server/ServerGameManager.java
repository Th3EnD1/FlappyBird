/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import flappy_bird.*;
import static flappy_bird.GameManager.game;
import static flappy_bird.GameManager.height;
import static flappy_bird.GameManager.width;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author student
 */
public class ServerGameManager extends Thread
{
    InitServer server;
    public static ServerGameManager game;
    
    Bird birdOne;
    Bird birdTwo;
    public ArrayList<Pipe> pipes;
    public ArrayList<Coin> coins;
    public ArrayList<Missile> missiles;
    
    Image backgroundImage;
    public static int width;
    public static int height;
    
    static Image coinImage = ((new ImageIcon("coin.gif")).getImage()).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    
    boolean gameActive, started;
    int scoreOne, scoreTwo;
    long lastPipe;
    long pipeSpawnTime;
    long cooldownTime;
    boolean pipesDisabled;
    Random rnd;
    
    Pipe tempPipe;
    Coin tempCoin;
    Missile tempMissile;

    boolean isAlive;

    public ServerGameManager(InitServer server)
    {
        this.server = server;

        game = this;
        width = 1200;
        height = 700;
        lastPipe = 0;
        cooldownTime = 2500;// 2.5 seconds
        backgroundImage = (new ImageIcon("background.jpg")).getImage();
        birdOne = new Bird(this);
        birdTwo = new Bird(this);
        pipes = new ArrayList<Pipe>();
        coins = new ArrayList<Coin>();
        missiles = new ArrayList<Missile>();
        gameActive = false;
        started = false;
        rnd = new Random();
    }

    public void collision()
    {
        Pipe currentPipe;
        Missile currentMissile;
        
        for (int i = 0; i < pipes.size(); i++)
        {
            currentPipe = pipes.get(i);
            //collision of player one with the top part of the pipe
            if (birdOne.x < currentPipe.topPipeX + currentPipe.imageWidth && birdOne.x + birdOne.width > currentPipe.topPipeX && birdOne.y < currentPipe.topPipeY + currentPipe.imageHeight && birdOne.height + birdOne.y > currentPipe.topPipeY) 
            {
                gameActive = false;
                birdOne.isAlive = false;
            } 
            
            //collision of player one with the bottom part of the pipe
            if (birdOne.x < currentPipe.botPipeX + currentPipe.imageWidth && birdOne.x + birdOne.width > currentPipe.botPipeX && birdOne.y < currentPipe.botPipeY + currentPipe.imageHeight && birdOne.height + birdOne.y > currentPipe.botPipeY) 
            {
                gameActive = false;
                birdOne.isAlive = false;
            }

            //collision of player two with the top part of the pipe
            if (birdTwo.x < currentPipe.topPipeX + currentPipe.imageWidth && birdTwo.x + birdTwo.width > currentPipe.topPipeX && birdTwo.y < currentPipe.topPipeY + currentPipe.imageHeight && birdTwo.height + birdTwo.y > currentPipe.topPipeY) 
            {
                gameActive = false;
                birdTwo.isAlive = false;
            } 
            
            //collision of player two with the bottom part of the pipe
            if (birdTwo.x < currentPipe.botPipeX + currentPipe.imageWidth && birdTwo.x + birdTwo.width > currentPipe.botPipeX && birdTwo.y < currentPipe.botPipeY + currentPipe.imageHeight && birdTwo.height + birdTwo.y > currentPipe.botPipeY) 
            {
                gameActive = false;
                birdTwo.isAlive = false;
            }
        }
        
        for (int i = 0; i < missiles.size(); i++)
        {
            currentMissile = missiles.get(i);

            //collision of player one with the missile
            if (birdOne.x < currentMissile.x + currentMissile.imageWidth && birdOne.x + birdOne.width > currentMissile.x && birdOne.y < currentMissile.y + currentMissile.imageHeight && birdOne.height + birdOne.y > currentMissile.y)
            {
                gameActive = false;
                birdOne.isAlive = false;
            }

            //collision of player two with the missile
            if (birdTwo.x < currentMissile.x + currentMissile.imageWidth && birdTwo.x + birdTwo.width > currentMissile.x && birdTwo.y < currentMissile.y + currentMissile.imageHeight && birdTwo.height + birdTwo.y > currentMissile.y)
            {
                gameActive = false;
                birdTwo.isAlive = false;
            }
        }
        
        //collision of player one with the ground
        if (birdOne.y >= height - 115 || birdOne.y <= 0) 
        {
            gameActive = false;
            birdOne.isAlive = false;
        }

        //collision of player two with the ground
        if (birdTwo.y >= height - 115 || birdTwo.y <= 0) 
        {
            gameActive = false;
            birdTwo.isAlive = false;
        }
    }

    public void addScore()
    {
        for (int i = 0; i < pipes.size(); i++)
        {
            Pipe temp = pipes.get(i);
            if (!temp.scored && temp.x + temp.imageWidth <= birdOne.x)
            {
                temp.scored = true;
                scoreOne++;
            }
        }

        for (int i = 0; i < pipes.size(); i++) 
        {
            Pipe temp = pipes.get(i);
            if (!temp.scored && temp.x + temp.imageWidth <= birdTwo.x)
            {
                temp.scored = true;
                scoreTwo++;
            }
        }
    }

    public void randomizeCoins()
    {
        pipeSpawnTime = System.currentTimeMillis();
        Coin c;
        int numOfCoins = rnd.nextInt(7);
        if (numOfCoins != 0)
        {
            int interval = (int)(cooldownTime / numOfCoins);
            numOfCoins--;
            for (int i = 0; i < numOfCoins; i++)
            {
                c = new Coin(this, rnd.nextInt(400) + 150, interval * (i + 1));
                coins.add(c);
            }   
        }
    }

    public void missileSpawner()
    {
        Random randomNum = new Random();
        int rndNum = randomNum.nextInt(4) + 1;
        if(rndNum == 4 && missiles.size() < 1)
        {
            missiles.add(new Missile(this));
            pipesDisabled = true;
        }
    }

    public void coinsCollision()
    {
        for (int i = 0; i < coins.size(); i++)
        {
            //collision of player one with the coin
            if (singleCoinColl(coins.get(i), birdOne)) 
            {
                scoreOne++;
                coins.remove(i);
            }

            //collision of player two with the coin
            if (singleCoinColl(coins.get(i), birdTwo)) 
            {
                scoreOne++;
                coins.remove(i);
            }
        }
    }
    
    public boolean singleCoinColl(Coin c, Bird bird)
    {
        Point circleDistance = new Point();
            
        circleDistance.x = Math.abs(c.x - bird.x);
        circleDistance.y = Math.abs(c.y - bird.y);

        if (circleDistance.x > (bird.width/2 + c.r)){ return false; }
        if (circleDistance.y > (bird.height/2 + c.r)) { return false; }

        if (circleDistance.x <= (bird.width/2)) { return true; } 
        if (circleDistance.y <= (bird.height/2)) { return true; }

        int cornerDistance_sq = (circleDistance.x - bird.width/2)^2 +
                             (circleDistance.y - bird.height/2)^2;

        return (cornerDistance_sq <= (c.r^2));
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
}
