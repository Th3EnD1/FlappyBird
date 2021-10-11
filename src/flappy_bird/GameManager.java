/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author student
 */
public class GameManager extends JPanel
{
    Bird bird;
    Pipe pipe;
    public ArrayList<Pipe> pipes;
    Image backgroundImage;
    static int width;
    static int height;
    boolean started, gameOver;
    int score;
    
    public GameManager()
    {
        width = 1200;
        height = 700;
        backgroundImage = (new ImageIcon("background.jpg")).getImage();
        bird = new Bird(this);
        pipe = new Pipe(this);
        pipes = new ArrayList<Pipe>();
        
        pipes.add(new Pipe(this));
    }
    
    public void addPipe(boolean start)
    {
        if(start)
        {
            pipes.add(new Pipe(this));
        }
        else
        {
            
        }
    }
    
    public void Jump()
    {
        if(gameOver)
        {
            bird = new Bird(this);
            pipes.clear();
            bird.yMotion = 0;
            score = 0;
            
            //------ Call 4 times to addPipe with the parameter true in order ------
            //------ to spawn new pipes that fit to the start of the game     ------
            addPipe(true);
            addPipe(true);
            addPipe(true);
            addPipe(true);
            
            gameOver = true;
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
            
            bird.yMotion -= 10;
        }
    }
    
    public void paintComponent(Graphics g)	
    {
	super.paintComponent(g);
	g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),null);	
        bird.drawBird(g);
        
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
        //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //f.setUndecorated(true);
        f.setResizable(false);
        f.setVisible(true);	
        f.setFocusable(false);
        //gamePanel.hideMouseCursor();
    }
}


