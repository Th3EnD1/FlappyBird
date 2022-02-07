package client;

import flappy_bird.GameManager;

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
                    if (obj instanceof Integer) {

                    }
                    if (obj instanceof String) {

                    }
                    if (obj instanceof Boolean) {

                    }
                }
            } catch (java.io.IOException ex) {

            }

            catch (ClassNotFoundException ex) {

            }
        }
    }
}
