package client;

import javax.swing.JPanel;


public class ClientGamePanel extends JPanel
{
    //final String SERVER_IP = "79.183.186.221";
    final int PORT = 25565;
    java.net.Socket socket;
    public java.io.ObjectOutputStream objectOutputStream;
    public java.io.ObjectInputStream objectInputStream;
    
    ClientListener clientThread;
    
    public ClientGamePanel()
    {
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
