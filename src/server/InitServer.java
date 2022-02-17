package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// import flappy_bird.GameManager;

public class InitServer extends java.util.Observable {
    // public GameManager game;
    ServerSocket serverSocket;
    Socket socket;
    public Queue<Integer> playersQueue;
    private ArrayList<ServerListener> ServerListeners;

    public static void main(String[] args) {
        int port = 25565;
        new InitServer().handleClients(port);
    }

    public void handleClients(int port) {
        serverSocket = null;
        playersQueue = new LinkedList<>();
        ServerListeners = new ArrayList<ServerListener>();

        try {
            System.out.println("The Server is ready...");
            serverSocket = new ServerSocket(port);
            for (int i = 0;; i++) {
                socket = serverSocket.accept();
                System.out.println("A new client: " + i);
                // playersQueue[i - 1] = true;
                ServerListener serverListener = new ServerListener(this, i, socket);
                addObserver(serverListener);
                ServerListeners.add(serverListener);
                serverListener.start();
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

    public void addWaitingClients(int client) {
        System.out.println("Client " + client + " has been added to the waiting queue");
        int client1, client2;
        playersQueue.add(client);
        if (playersQueue.size() >= 2) {
            client1 = playersQueue.remove();
            client2 = playersQueue.remove();
            // createRoom(client1, client2);
        }
    }

    public void cancelClientSearching(int client) {
        if (playersQueue.contains(client)) {
            playersQueue.remove(client);
            ServerListeners.get(client).currentStatus = ServerListener.Status.LOBBY;
            System.out.println("Client " + client + " has been removed from queue");
        }
    }

    public void update(Object obj) {
        System.out.println("sent:" + obj.toString());
        setChanged();
        notifyObservers(obj);
    }

    public void remove(int index, ServerListener serverThread) {
        deleteObserver(serverThread);
        // playersQueue[index] = false;
        update("Client " + index + " has left.");
    }
}
