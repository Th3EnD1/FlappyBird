/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author student
 */
public class ServerListener extends Thread implements java.util.Observer {
    public enum Status {
        LOBBY,
        WAITING_IN_QUEUE,
        IN_MATCH
    }

    private InitServer server;
    private int index;
    private java.net.Socket socket;
    private java.io.ObjectInputStream in;
    private java.io.ObjectOutputStream out;
    public Status currentStatus;

    public ServerListener(InitServer server, int index, java.net.Socket socket) {
        this.server = server;
        this.index = index;
        this.socket = socket;
        this.currentStatus = Status.LOBBY;
    }

    public void run() {
        try {
            in = new java.io.ObjectInputStream(socket.getInputStream());
            out = new java.io.ObjectOutputStream(socket.getOutputStream());

            // if (index < 2)
            // {
            // out.writeObject(new Integer(1));
            // }
            // else
            // {
            // out.writeObject(new Integer(2));
            // }

            out.flush();
            Object obj;
            while ((obj = in.readObject()) != null) {
                if (obj instanceof String) {
                    switch ((String) obj) {
                        case "Join": {
                            if (currentStatus == Status.LOBBY) {
                                server.addWaitingClients(index);
                                currentStatus = Status.WAITING_IN_QUEUE;
                            }
                        }
                            break;
                        case "Cancel":
                            server.cancelClientSearching(index);
                            break;
                    }
                }
                ServerData temp = (ServerData) obj;
                temp.player = index;
                server.update(temp);
                System.out.println("Got" + temp.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                in.close();
            } catch (Exception e2) {
            }
            try {
                out.close();
            } catch (Exception e2) {
            }
            try {
                socket.close();
            } catch (Exception e2) {
            }
            server.remove(index, this);
        }
    }

    public void update(java.util.Observable o, Object arg) {
        try {
            out.writeObject(arg);
            out.flush();
        } catch (java.io.IOException ex) {

        }
    }
}
