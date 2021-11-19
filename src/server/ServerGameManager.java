/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import flappy_bird.*;
import static flappy_bird.GameManager.game;
import static flappy_bird.GameManager.height;
import static flappy_bird.GameManager.width;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author student
 */
public class ServerGameManager extends Thread
{
    public InitServer server;
    
    public ArrayList<Pipe> pipes;
    public ArrayList<Coin> coins;
    public ArrayList<Missile> missiles;
    
    Image backgroundImage;
    public static int width;
    public static int height;
    
    static Image coinImage = ((new ImageIcon("coin.gif")).getImage()).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    
    boolean gameActive, started;
    int score;
    long lastPipe;
    long pipeSpawnTime;
    long cooldownTime;
    boolean pipesDisabled;
    Random rnd;
    
    Pipe tempPipe;
    Coin tempCoin;
    Missile tempMissile;
    
    public ServerGameManager(InitServer server)
    {
        this.server = server;
        
        width = 1200;
        height = 700;
        lastPipe = 0;
        cooldownTime = 2500;// 2.5 seconds
        backgroundImage = (new ImageIcon("background.jpg")).getImage();
        pipes = new ArrayList<Pipe>();
        coins = new ArrayList<Coin>();
        missiles = new ArrayList<Missile>();
        gameActive = false;
        started = false;
        rnd = new Random();
        
        score = 0;
        
//        addMouseListener(new MouseAdapter() 
//        { 
//            public void mousePressed(MouseEvent me) 
//            { 
//                if (gameActive) 
//                {
//                    bird.resetMotion();
//                    bird.yMotion -= 14;
//                }
//                else
//                {
//                    bird = new Bird(game);
//                    pipes.clear();
//                    coins.clear();
//                    missiles.clear();
//                    bird.yMotion = 0;
//                    score = 0;
//                    gameActive = true;
//                    started = true;
//                }
//            }
//        });

        
    }
}
