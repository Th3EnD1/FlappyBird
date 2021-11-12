/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Eden
 */
public class Menu extends JPanel implements ActionListener
{
    private static Image menuImage = (new ImageIcon("menuBG.png")).getImage();
    private static double mult = 0.5;
    public static int width = (int)(menuImage.getWidth(null) * mult);
    public static int height = (int)(menuImage.getHeight(null) * mult);
    public static int btn_Width = 150, btn_Height = 30;
    public GameFrame frame;
    private JButton btnStartSingle, btnJoin, btnCancel;
    
    public Menu(GameFrame f)
    {
        initGUI();
        this.frame = f;
    }
    
    public void initGUI()
    {
        setLayout(null);
//        this.setPreferredSize(new Dimension(width, height));
//        frame = new JFrame("Sloppy Bird by Eden");
//        frame.add(this);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocation(200, 200);
        
        btnJoin = new JButton("Join queue");
        //btnJoin.setBorderPainted(false); 
        //btnJoin.setContentAreaFilled(false); 
        //startImage = startImage.getScaledInstance(300, 100, Image.SCALE_DEFAULT);
        //btnJoin.setIcon(new ImageIcon(startImage));
        btnJoin.setBounds((width / 2) - 300, 250, btn_Width, btn_Height);
        add(btnJoin);
        btnJoin.setActionCommand("Join");
        
        btnCancel = new JButton("Cancel Search");
        //btnCancel.setBorderPainted(false); 
        //btnCancel.setContentAreaFilled(false); 
        //startImage = startImage.getScaledInstance(300, 100, Image.SCALE_DEFAULT);
        //btnJoin.setIcon(new ImageIcon(startImage));
        btnCancel.setBounds((width / 2) - 300, 300, btn_Width, btn_Height);
        add(btnCancel);
        btnCancel.setActionCommand("Cancel");
        
        btnStartSingle = new JButton("Start Game");
        //btnCancel.setBorderPainted(false); 
        //btnCancel.setContentAreaFilled(false); 
        //startImage = startImage.getScaledInstance(300, 100, Image.SCALE_DEFAULT);
        //btnJoin.setIcon(new ImageIcon(startImage));
        btnStartSingle.setBounds((width / 2) + 100, 275, btn_Width, btn_Height);
        add(btnStartSingle);
        btnStartSingle.setActionCommand("Start");
        btnStartSingle.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Started1");
                frame.updateFrame(1);
            }
        });
        
        //frame.setVisible(true);
        //frame.pack();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, width, height, null);
        g.setFont(new Font("Arial", 2, 20));
        g.drawString("MULTIPLAYER", (width / 2) - 300, 225);
        g.drawString("SINGLE PLAYER", (width / 2) + 100, 225);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Join":
            {
                
            }break;
            
            case "Cancel":
            {
                
            }break;
            
            case "Start":
            {
                System.out.println("Started");
                this.frame.updateFrame(1);
            }break;
        }
    }
}
