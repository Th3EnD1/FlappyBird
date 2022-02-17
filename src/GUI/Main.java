/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import server.InitServer;

/**
 *
 * @author Eden
 */
public class Main {
    public static void main(String[] args) {
        int port = 25565;
        new GameFrame();
        new InitServer().handleClients(port);
    }
}
