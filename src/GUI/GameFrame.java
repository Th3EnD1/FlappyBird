/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import flappy_bird.GameManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import flappy_bird.GameManager;
import java.awt.Dimension;
import client.ClientGamePanel;

public class GameFrame extends JFrame
{
    public JFrame f;
    public JPanel panel;
    
    int frameNumber;
    
    public GameFrame()
    {
        this.setTitle("Flappy Bird By Eden");
        panel = new Menu(this);
        this.add(panel);
        //this.setPreferredSize(new Dimension(Menu.width, Menu.height));
        this.setSize(Menu.width, Menu.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(200, 200);
        this.setVisible(true);	
        this.setFocusable(false);
    }
    
    public void updateFrame(int frameNumber)
    {
        this.frameNumber = frameNumber;
        
        switch(frameNumber)
        {
            case 1:
            {
                this.getContentPane().removeAll();
                panel = new GameManager();
                
                this.add(panel);
                this.setSize(GameManager.width,GameManager.height);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setLocation(200, 200);
                this.setVisible(true);	
                this.setFocusable(false);
            }break;
            
            case 2:
            {
                //ClientGamePanel run = new ClientGamePanel();
                this.getContentPane().removeAll();
                panel = new ClientGamePanel();
                
                this.add(panel);
                this.setSize(ClientGamePanel.width,ClientGamePanel.height);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setLocation(200, 200);
                this.setVisible(true);	
                this.setFocusable(false);
            }
        }
    }
}
