package server;

import java.io.Serializable;
import javax.swing.ImageIcon;
import flappy_bird.GameManager;

public class ServerData implements Serializable 
{
    private static final long serialVersionUID = 1L;

    public int player;
    public int score;
    public boolean gameActive;
    public int state; //1 = player starts the game; 2 = player finished the game

    public ServerData(GameManager game, int state) 
    {
        this.player = game.player;
        this.score = game.score;
        this.gameActive = game.gameActive;
        this.state = state;
    }

    @Override
    public String toString() 
    {
        return "ServerData [state=" + state + ", player=" + player + ", gameActive=" + ", score=" + score + "]";
    }

}
