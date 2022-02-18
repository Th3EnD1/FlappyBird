package client;

import flappy_bird.GameManager;
import server.ServerData;

public class ClientListener extends Thread {
    GameManager clientPanel;

    public ClientListener(GameManager clientPanel) {
        this.clientPanel = clientPanel;
    }

    public void run() {
        while (true) {
            try {
                Object obj;
                while ((obj = clientPanel.objectInputStream.readObject()) != null) 
                {
                    if (obj instanceof ServerData) 
                    {
                        ServerData temp = (ServerData) obj;
                        if (temp.player != clientPanel.player) 
                        {
                            clientPanel.oppScore = temp.score;
                            clientPanel.oppDead = true;
                        }
                    }
                    
                    if (obj instanceof Integer) 
                    {
                        clientPanel.player = (Integer) obj;
                    }
                    
                    if (obj instanceof String) 
                    {
                        String temp = (String) obj;
                        if ("start".equals(temp))
                        {
                            clientPanel.waiting = false;
                            clientPanel.gameActive = false;
                        }
                    }
                }
            } catch (java.io.IOException ex) {

            }

            catch (ClassNotFoundException ex) {

            }
        }
    }
}
