/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Eden
 */
public class Menu extends JPanel implements ActionListener {
    private static Image menuImage = (new ImageIcon("menuBG.png")).getImage();
    private static double mult = 0.5;
    public static final int width = (int) (menuImage.getWidth(null) * mult);
    public static final int height = (int) (menuImage.getHeight(null) * mult);
    public static int btn_Width;// = 150, btn_Height = 30;
    public static int btn_Height;
    public GameFrame frame;
    private JButton btnStartSingle, btnJoin, btnCancel;

    public Menu(GameFrame f) {
        initGUI();
        this.frame = f;
    }

    public void initGUI() {
        setLayout(null);

        menuImage = (new ImageIcon("menuBG.png")).getImage();
        mult = 0.5;
        // width = 1200;
        // height = 700;
        btn_Width = 150;
        btn_Height = 30;

        btnJoin = new JButton("Join queue");
        btnJoin.setBounds((width / 2) - 300, 250, btn_Width, btn_Height);
        add(btnJoin);
        btnJoin.setActionCommand("Join");
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Started multiplayer");
                frame.updateFrame(2);
            }
        });

        btnCancel = new JButton("Cancel Search");
        btnCancel.setBounds((width / 2) - 300, 300, btn_Width, btn_Height);
        add(btnCancel);
        btnCancel.setActionCommand("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Canceled multiplayer");
            }
        });

        btnStartSingle = new JButton("Start Game");
        btnStartSingle.setBounds((width / 2) + 100, 275, btn_Width, btn_Height);
        add(btnStartSingle);
        btnStartSingle.setActionCommand("Start");
        btnStartSingle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Started single player");
                frame.updateFrame(1);
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, width, height, null);
        g.setFont(new Font("Arial", 2, 20));
        g.drawString("MULTIPLAYER", (width / 2) - 300, 225);
        g.drawString("SINGLE PLAYER", (width / 2) + 100, 225);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Action");
        // switch (e.getActionCommand())
        // {
        // case "Join":
        // {
        // //ClientGamePanel run = new ClientGamePanel();
        // }break;
        //
        // case "Cancel":
        // {
        //
        // }break;
        //
        // case "Start":
        // {
        // System.out.println("Started");
        // this.frame.updateFrame(1);
        // }break;
        // }
    }
}
