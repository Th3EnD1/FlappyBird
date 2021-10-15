/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author student
 */
public class GameManager extends JPanel
{
    public static GameManager game;
    
    Bird bird;
    public ArrayList<Pipe> pipes;
    
    Image backgroundImage;
    static int width;
    static int height;
    
    boolean gameActive, started;
    int score;
    long lastPipe;
    long cooldownTime;
    
    public GameManager()
    {
        game = this;
        width = 1200;
        height = 700;
        lastPipe = 0;
        cooldownTime = 1500;// 2000 milliseconds
        backgroundImage = (new ImageIcon("background.jpg")).getImage();
        bird = new Bird(this);
        pipes = new ArrayList<Pipe>();
        gameActive = false;
        started = false;
        
        addMouseListener(new MouseAdapter() 
        { 
            public void mousePressed(MouseEvent me) 
            { 
                if (gameActive) 
                {
                    bird.resetMotion();
                    bird.yMotion -= 14;
                }
                else
                {
                    bird = new Bird(game);
                    pipes.clear();
                    bird.yMotion = 0;
                    score = 0;
                    gameActive = true;
                    started = true;
                }
            }
        });
    }
    
    public void collision()
    {
        Pipe currentPipe;
        for (int i = 0; i < pipes.size(); i++)
        {
            currentPipe = pipes.get(i);
            //collision with the top part of the pipe
            if (bird.x < currentPipe.topPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.topPipeX && bird.y < currentPipe.topPipeY + currentPipe.imageHeight && bird.height + bird.y > currentPipe.topPipeY) 
            {
                gameActive = false;
                System.out.println("top");
            } 
            
            //collision with the bottom part of the pipe
            if (bird.x < currentPipe.botPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.botPipeX && bird.y < currentPipe.botPipeY + currentPipe.imageHeight && bird.height + bird.y > currentPipe.botPipeY) 
            {
                gameActive = false;
                System.out.println("bot");
            }
        }
        
        if (bird.y >= height - 115 || bird.y <= 0) 
        {
            gameActive = false;
        }
    }
    
    public void addScore()
    {
        for (int i = 0; i < pipes.size(); i++) 
        {
            Pipe temp = pipes.get(i);
            if (!temp.scored && temp.x + temp.imageWidth <= bird.x) 
            {
                temp.scored = true;
                score++;
                System.out.println(score);
            }
        }
    }
    
    public void paintComponent(Graphics g)	
    {
	super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),null);
        g.setColor(Color.white);
        
        if (gameActive) 
        {
            collision();
            addScore();
            bird.drawBird(g);
        
            //Spawns pipes on cooldown
            long time = System.currentTimeMillis();
            if (time > lastPipe + cooldownTime && gameActive)
            {
                pipes.add(new Pipe(this));
                lastPipe = time;
            }
        
            //Draws the pipes
            for (int i = 0; i < pipes.size(); i++) 
            {
                Pipe temp = pipes.get(i);
                if (temp.isAlive)
                {
                    temp.drawPipe(g);
                }
                else
                {
                    pipes.remove(temp);
                }
            }
        }
        else if(started)
        {
            bird.drawBird(g);
            
            for (int i = 0; i < pipes.size(); i++) 
            {
                Pipe temp = pipes.get(i);
                if (temp.isAlive)
                {
                    temp.drawPipe(g);
                    temp.frozen = true;
                }
                else
                {
                    pipes.remove(temp);
                }
            }
            g.setFont(new Font("Arial", 1, 150));
            g.drawString("Game Over!", 190, 375);
            g.setFont(new Font("Arial", 1, 40));
            g.drawString("Click To Start Again", 450, 450);
        }
        else 
        {
            g.setFont(new Font("Arial", 1, 150));
            g.drawString("Click To Start!", 110, 375);
        }
        g.setFont(new Font("Arial", 1, 75));
        g.drawString("" + score, 580, 100);
    }
    
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Flappy Bird By Eden");
        GameManager gamePanel = new GameManager();
        f.add(gamePanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(gamePanel.width,gamePanel.height);
        f.setResizable(false);
        f.setVisible(true);	
        f.setFocusable(false);
    }
}


