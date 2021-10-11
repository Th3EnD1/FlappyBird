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
    ArrayList<Pipe> pipes;
    Image backgroundImage;
    static int width;
    static int height;
    boolean started, gameOver;
    
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
    
    public void Jump()
    {
        if(gameOver)
        {
            
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


