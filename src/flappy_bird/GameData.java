/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappy_bird;

import java.io.Serializable;

public class GameData implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int state;
    public int playerIndex;
    public int birdY;

    // Used to send the bird's location
    public GameData(int playerIndex, int birdY)
    {
        this.state = 0;
        this.playerIndex = playerIndex;
        this.birdY = birdY;
    }

    public String toString()
    {
        return (playerIndex + " " + birdY);
    }
}
