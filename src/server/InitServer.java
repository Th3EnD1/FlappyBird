package server;

public class InitServer extends java.util.Observable {
    public int readyCounter = 0;
    public int gameOverCounter = 0;

    public static void main(String[] args) {
        int port = 25565;
        new InitServer().handleClients(port);
    }

    public void handleClients(int port) {
        java.net.ServerSocket serverSocket = null;
        try {
            System.out.println("The Server is ready...");
            serverSocket = new java.net.ServerSocket(port);
            for (int i = 1;; i++) {
                java.net.Socket socket = serverSocket.accept();
                update("A new client: " + i);
                ServerListener clientThread = new ServerListener(this, i, socket);
                addObserver(clientThread);
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (java.io.IOException x) {
                }
        }
    }

    public boolean readyCheck() 
    {
        if (readyCounter == 2)
            return true;
        else
            return false;
    }

    public void update(Object obj) {
        System.out.println("sent:" + obj.toString());
        setChanged();
        notifyObservers(obj);
    }

    public void remove(int index, ServerListener clientThread) {
        deleteObserver(clientThread);
        // update("Client " + index + " has left.");
    }
}
