package flappy.bird;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Eden
 */
public class Renderer extends JPanel
{

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        FlappyBird.flappyBird.repaint(g);
    }
    
}
