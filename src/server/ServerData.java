package server;

import java.io.Serializable;
import javax.swing.ImageIcon;
import flappy_bird.GameManager;

public class ServerData implements Serializable 
{
    private static final long serialVersionUID = 1L;

    public int player;
    public int score;

    public ServerData(GameManager game) 
    {
        this.player = game.player;
        this.score = game.score;
    }

    @Override
    public String toString() 
    {
        return "ServerData [player=" + player + ", score=" + score + "]";
    }

}
