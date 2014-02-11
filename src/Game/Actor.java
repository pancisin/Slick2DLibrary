package Game;

import System.World;
import org.newdawn.slick.Image;

/**
 *
 * @author Patrik
 */
public interface Actor {

    public void setPosition(int i, int j);

    public Image getImage();

    public int getX();

    public int getY();

    public void act();

    public void setX(int i);

    public void setY(int i);

    public void setWorld(World wrld);

}
