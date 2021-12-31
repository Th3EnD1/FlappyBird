package client;

import GUI.GameFrame;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import flappy_bird.Bird;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ClientGamePanel extends JPanel
{
    public static ClientGamePanel clientGame;

    //final String SERVER_IP = "79.183.186.221";
    final int PORT = 25565;
    java.net.Socket socket;
    public java.io.ObjectOutputStream objectOutputStream;
    public java.io.ObjectInputStream objectInputStream;
    Image backgroundImage;
    public static int width;
    public static int height;
    public Bird bird;
    boolean gameActive, started;

    
    ClientListener clientThread;
    
    public ClientGamePanel()
    {
        clientGame = this;
        width = 1200;
        height = 700;
        gameActive = false;
        started = false;
        bird = new Bird(this);
        backgroundImage = (new ImageIcon("background.jpg")).getImage();

        addMouseListener(new MouseAdapter() 
        { 
            public void mousePressed(MouseEvent me) 
            { 
                System.out.println("Mouse Pressed");
                send("Mouse Pressed");
                if (gameActive) 
                {
                    bird.resetMotion();
                    bird.yMotion -= 14;
                }
                else
                {
                    bird = new Bird(clientGame);
                    //pipes.clear();
                    //coins.clear();
                    //missiles.clear();
                    bird.yMotion = 0;
                    //score = 0;
                    gameActive = true;
                    bird.isAlive = true;
                    started = true;
                }
            }
        });

        this.clientThread = new ClientListener(this);
        this.connect();
        this.clientThread.start();
    }
    
    public void connect()
    {
        try 
        {
            //this.socket = new java.net.Socket(java.net.InetAddress.getByName("79.183.186.221"), PORT);
            this.socket = new java.net.Socket("localhost", PORT);
            this.objectOutputStream = new java.io.ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new java.io.ObjectInputStream(this.socket.getInputStream());
        }
        catch (java.io.IOException ex) 
        { 
            
        }
    }

    public void paintComponent(Graphics g)	
    {
	super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),null);
        bird.drawBird(g);
        send(bird.birdLocation);
    }
    
    public void send(Object obj) 
    {
        try 
        {
            this.objectOutputStream.writeObject(obj);
            this.objectOutputStream.flush();
        }
        catch (java.io.IOException ex) 
        {
            
        }
    }
}
