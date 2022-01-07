/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import flappy_bird.GameData;

/**
 *
 * @author student
 */
public class ServerListener extends Thread implements java.util.Observer
{
    private InitServer server;
    private int index;
    private java.net.Socket socket;
    private java.io.ObjectInputStream in;
    private java.io.ObjectOutputStream out;
    private GameData tempData;
    
    public ServerListener (InitServer server, int index, java.net.Socket socket) 
    {
        this.server = server;
        this.index = index;
        this.socket = socket;
    }
    
    public void run() 
    {
        try 
        {
            in = new java.io.ObjectInputStream(socket.getInputStream());
            out = new java.io.ObjectOutputStream(socket.getOutputStream());
            out.writeObject(index);
            
            out.flush();
            Object obj;
            while ((obj = in.readObject()) != null) 
            {
                if (obj instanceof String) 
                {
                    
                }
                if (obj instanceof int[])
                {
                    
                }
                if (obj instanceof Integer) 
                {
                    
                }
                if (obj instanceof GameData)
                {
                    //tempData = (GameData)obj;
                    //System.out.println(tempData.toString());
                    //server.update(tempData);
                    //((GameData)obj).toString();
                    switch (((GameData)obj).state)
                    {
                        case 0:
                            {
                                System.out.println(((GameData) obj).toString());
                            }break;
                        default:
                            throw new AssertionError();
                    }
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally 
        {
            try { in.close(); } catch (Exception e2) { }
            try { out.close(); } catch (Exception e2) { }
            try { socket.close(); } catch (Exception e2) { }
            server.remove(index, this);
        }
    }
    
    public void update(java.util.Observable o, Object arg) 
    {
        try 
        {
            out.writeObject(arg);
            out.flush();
        } 
        catch (java.io.IOException ex) 
        {
            
        }
    }
}
