package server;

import java.io.Serializable;

import javax.swing.ImageIcon;

import flappy_bird.GameManager;

public class ServerData implements Serializable {
    private static final long serialVersionUID = 1L;

    public int player;
    public ImageIcon screen = new ImageIcon();
    int score;
    // State 1 is update image
    // State 2 is send score
    int state;

    public ServerData(GameManager game, int state) {
        this.state = state;
        this.player = game.player;
        this.score = game.score;

        if (game.gameScreen != null) {
            this.screen = new ImageIcon(game.gameScreen);
        }
    }

    @Override
    public String toString() {
        return "ServerData [player=" + player + ", score=" + score + ", state=" + state + "]";
    }

}
