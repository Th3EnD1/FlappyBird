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
    Pipe pipe;
    public ArrayList<Pipe> pipes;
    Image backgroundImage;
    static int width;
    static int height;
    boolean started, gameOver;
    int score;
    long lastPipe;
    long cooldownTime;
    
    public GameManager()
    {
        width = 1200;
        height = 700;
        lastPipe = 0;
        cooldownTime = 1500;// 2000 milliseconds
        backgroundImage = (new ImageIcon("background.jpg")).getImage();
        bird = new Bird(this);
        pipe = new Pipe(this);
        pipes = new ArrayList<Pipe>();
        
        addMouseListener(new MouseAdapter() 
        { 
            public void mousePressed(MouseEvent me) 
            { 
                jump();
            }
        });
    }
    
    public void jump()
    {
        if(gameOver)
        {
            bird = new Bird(this);
            pipes.clear();
            bird.yMotion = 0;
            score = 0;
            gameOver = false;
        }
        
        if(!started)
        {
            started = true;
        }
        else if (!gameOver)
        {
            if(bird.yMotion > 0)
            {
                bird.yMotion = 0;
            }
            
            bird.yMotion -= 14;
        }
    }
    
    public void collision()
    {
        Pipe currentPipe;
        for (int i = 0; i < pipes.size(); i++)
        {
            currentPipe = pipes.get(i);
            if (bird.x < currentPipe.topPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.topPipeX && bird.y < currentPipe.topPipeY + currentPipe.imageHeight && bird.height + bird.y > currentPipe.topPipeY) 
            {
                System.out.println("top");
            } 
            
            if (bird.x < currentPipe.botPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.botPipeX && bird.y < currentPipe.botPipeY + currentPipe.imageHeight && bird.height + bird.y > currentPipe.botPipeY) 
            {
                System.out.println("bot");
            }
        }
    }
    
    public void paintComponent(Graphics g)	
    {
	super.paintComponent(g);
	g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),null);
        bird.drawBird(g);
        
        collision();
        long time = System.currentTimeMillis();
        if (time > lastPipe + cooldownTime && started)
        {
            pipes.add(new Pipe(this));
            lastPipe = time;
        }
        
        for (int i = 0; i < pipes.size(); i++) 
        {
            Pipe temp = pipes.get(i);
            
            if (temp.isAlive)
            {
                temp.drawPipe(g);
                //addPipe(started);
            }
            else
            {
                pipes.remove(temp);
                System.out.println("pipes deleted");
            }
        }
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


