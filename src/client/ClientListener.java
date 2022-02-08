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
                while ((obj = clientPanel.objectInputStream.readObject()) != null) {
                    if (obj instanceof ServerData) {
                        clientPanel.enemyData = (ServerData) obj;
                        System.out.println("got: " + clientPanel.enemyData.toString());
                    }
                    if (obj instanceof Integer) {
                        clientPanel.player = (Integer) obj;
                        // System.out.println(clientPanel.player);
                    }
                }
            } catch (java.io.IOException ex) {

            }

            catch (ClassNotFoundException ex) {

            }
        }
    }
}
