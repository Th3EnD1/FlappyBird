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
                        ServerData data = (ServerData) obj;
                        if (data.state == 1)
                        {
                            if (data.player != clientPanel.player) 
                            {
                                clientPanel.oppDead = false;
                                clientPanel.oppWaiting = false;
                            }
                        }
                        if (data.state == 2)
                        {
                            if (data.player != clientPanel.player) 
                            {
                                clientPanel.oppScore = data.score;
                                clientPanel.oppDead = true;
                                clientPanel.oppWaiting = true;
                            }
                        }
                    }
                    
                    if (obj instanceof Integer) 
                    {
                        clientPanel.player = (Integer) obj;
                    }
                    
                    if (obj instanceof String) 
                    {
                        String str = (String) obj;
                        if ("start".equals(str))
                        {
                            clientPanel.waiting = false;
                            clientPanel.oppWaiting = false;
                            clientPanel.gameActive = false;
                        }
                        
                        if ("GameOver".equals(str))
                        {
                            clientPanel.gameOver = true;
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
