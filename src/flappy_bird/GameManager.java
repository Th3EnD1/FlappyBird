/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappy_bird;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import client.ClientListener;
import server.ServerData;

/**
 *
 * @author Eden
 */
public class GameManager extends JPanel {
    // Multiplayer

    final int PORT = 25565;
    java.net.Socket socket;
    public java.io.ObjectOutputStream objectOutputStream;
    public java.io.ObjectInputStream objectInputStream;

    ClientListener clientListener;
    public BufferedImage gameScreen;
    public ServerData enemyData;
    public int player;
    boolean multiPlayer;

    // Single player

    public static GameManager game;

    public static int width;
    public static int height;

    Bird bird;
    Pipe tempPipe;
    Coin tempCoin;
    Missile tempMissile;

    public ArrayList<Pipe> pipes;
    public ArrayList<Coin> coins;
    public ArrayList<Missile> missiles;

    Image backgroundImage;
    static Image coinImage = ((new ImageIcon("coin.gif")).getImage()).getScaledInstance(30, 30, Image.SCALE_DEFAULT);

    boolean gameActive, started;
    public int score;
    long lastPipe;
    long pipeSpawnTime;
    long cooldownTime;
    boolean pipesDisabled;
    Random rnd;

    public GameManager(boolean multiPlayer) {
        this.multiPlayer = multiPlayer;
        if (multiPlayer) {
            // enemyData = new Data(this,0);
            player = 0;
            this.clientListener = new ClientListener(this);
            this.connect();
            this.clientListener.start();
        }

        // Initializing the game panel and game objects

        game = this;
        width = 1024;// 1200;
        height = 700;
        backgroundImage = (new ImageIcon("background.jpg")).getImage();

        gameActive = false;
        started = false;

        bird = new Bird(this);
        pipes = new ArrayList<Pipe>();
        coins = new ArrayList<Coin>();
        missiles = new ArrayList<Missile>();

        lastPipe = 0;
        cooldownTime = 2500;// 2.5 seconds
        rnd = new Random();

        // Mouse functionality

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (gameActive) {
                    bird.resetMotion();
                    bird.yMotion -= 14;
                } else {
                    bird = new Bird(game);
                    pipes.clear();
                    coins.clear();
                    missiles.clear();
                    bird.yMotion = 0;
                    score = 0;
                    gameActive = true;
                    started = true;
                }
            }
        });
    }

    public void collision() {
        Pipe currentPipe;
        Missile currentMissile;

        for (int i = 0; i < pipes.size(); i++) {
            currentPipe = pipes.get(i);
            // collision with the top part of the pipe
            if (bird.x < currentPipe.topPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.topPipeX
                    && bird.y < currentPipe.topPipeY + currentPipe.imageHeight
                    && bird.height + bird.y > currentPipe.topPipeY) {
                gameActive = false;
            }

            // collision with the bottom part of the pipe
            if (bird.x < currentPipe.botPipeX + currentPipe.imageWidth && bird.x + bird.width > currentPipe.botPipeX
                    && bird.y < currentPipe.botPipeY + currentPipe.imageHeight
                    && bird.height + bird.y > currentPipe.botPipeY) {
                gameActive = false;
            }
        }

        for (int i = 0; i < missiles.size(); i++) {
            currentMissile = missiles.get(i);
            // collision with the missile
            if (bird.x < currentMissile.x + currentMissile.imageWidth && bird.x + bird.width > currentMissile.x
                    && bird.y < currentMissile.y + currentMissile.imageHeight
                    && bird.height + bird.y > currentMissile.y) {
                gameActive = false;
            }
        }

        if (bird.y >= height - 115 || bird.y <= 0) {
            gameActive = false;
        }
    }

    public void addScore() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe temp = pipes.get(i);
            if (!temp.scored && temp.x + temp.imageWidth <= bird.x) {
                temp.scored = true;
                score++;
            }
        }
    }

    public void randomizeCoins() {
        pipeSpawnTime = System.currentTimeMillis();
        Coin c;
        int numOfCoins = rnd.nextInt(7);
        if (numOfCoins != 0) {
            int interval = (int) (cooldownTime / numOfCoins);
            numOfCoins--;
            for (int i = 0; i < numOfCoins; i++) {
                c = new Coin(this, rnd.nextInt(400) + 150, interval * (i + 1));
                coins.add(c);
            }
        }
    }

    public void missileSpawner() {
        Random randomNum = new Random();
        int rndNum = randomNum.nextInt(4) + 1;
        if (rndNum == 4 && missiles.size() < 1) {
            missiles.add(new Missile(this));
            pipesDisabled = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);

        if (gameActive) {
            collision();
            coinsCollision();
            addScore();
            bird.drawBird(g);

            // Spawns pipes or miislise on cooldown
            long time = System.currentTimeMillis();
            if (time > lastPipe + cooldownTime && gameActive) {
                missileSpawner();
                if (!pipesDisabled) {
                    pipes.add(new Pipe(this));
                    lastPipe = time;
                    randomizeCoins();
                }
            }

            // Draws the pipes
            for (int i = 0; i < pipes.size(); i++) {
                tempPipe = pipes.get(i);
                if (tempPipe.isAlive) {
                    tempPipe.drawPipe(g);
                } else {
                    pipes.remove(tempPipe);
                }
            }

            // Draws the coins
            for (int i = 0; i < coins.size(); i++) {
                tempCoin = coins.get(i);
                if (tempCoin.isAlive) {
                    tempCoin.drawCoin(g);
                } else {
                    coins.remove(tempCoin);
                }
            }

            // Draws the missiles
            for (int i = 0; i < missiles.size(); i++) {
                tempMissile = missiles.get(i);
                if (tempMissile.isAlive) {
                    tempMissile.drawMissile(g);
                    tempMissile.drawWarning(g);
                } else {
                    missiles.remove(tempMissile);
                    pipesDisabled = false;
                }
            }
        } else if (started) {
            bird.drawBird(g);

            for (int i = 0; i < pipes.size(); i++) {
                Pipe temp = pipes.get(i);
                if (temp.isAlive) {
                    temp.drawPipe(g);
                    temp.frozen = true;
                } else {
                    pipes.remove(temp);
                }
            }
            g.setFont(new Font("Arial", 1, 150));
            g.drawString("Game Over!", 190, 375);
            g.setFont(new Font("Arial", 1, 40));
            g.drawString("Click To Start Again", 450, 450);
        } else {
            g.setFont(new Font("Arial", 1, 150));
            g.drawString("Click To Start!", 110, 375);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 75));
        g.drawString("" + score, 580, 100);

        if (multiPlayer) {
            // screenShot();
            send(new ServerData(this, 1));
        }
    }

    public void coinsCollision() {
        for (int i = 0; i < coins.size(); i++) {
            if (singleCoinColl(coins.get(i))) {
                score++;
                coins.remove(i);
            }
        }
    }

    public boolean singleCoinColl(Coin c) {
        Point circleDistance = new Point();

        circleDistance.x = Math.abs(c.x - bird.x);
        circleDistance.y = Math.abs(c.y - bird.y);

        if (circleDistance.x > (bird.width / 2 + c.r)) {
            return false;
        }
        if (circleDistance.y > (bird.height / 2 + c.r)) {
            return false;
        }

        if (circleDistance.x <= (bird.width / 2)) {
            return true;
        }
        if (circleDistance.y <= (bird.height / 2)) {
            return true;
        }

        int cornerDistance_sq = (circleDistance.x - bird.width / 2) ^ 2 +
                (circleDistance.y - bird.height / 2) ^ 2;

        return (cornerDistance_sq <= (c.r ^ 2));
    }

    // Multiplayer functions

    public void connect() {
        try {
            // java.net.Socket(java.net.InetAddress.getByName("79.183.186.221"), PORT);
            this.socket = new java.net.Socket("localhost", PORT);
            this.objectOutputStream = new java.io.ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new java.io.ObjectInputStream(this.socket.getInputStream());
        } catch (java.io.IOException ex) {

        }
    }

    public void send(Object obj) {
        try {
            this.objectOutputStream.writeObject(obj);
            this.objectOutputStream.flush();
        } catch (java.io.IOException ex) {

        }
    }

    public void screenShot() {
        try {
            Point p = getLocationOnScreen();
            Dimension dim = new Dimension(1024, 700);
            Rectangle rect = new Rectangle(p, dim);
            gameScreen = (new Robot()).createScreenCapture(rect);
        } catch (Exception e) {
        }
    }
}
