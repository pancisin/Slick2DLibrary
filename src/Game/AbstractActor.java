package Game;

import System.World;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Patrik
 */
public abstract class AbstractActor implements Actor {

    private int xpos, ypos;
    private int width, height;
    private Image image;
    private World world;
    private double rotation;

    @Override
    public void setPosition(int i, int j) {
        xpos = i;
        ypos = j;
    }

    public void setImage(String string, int h, int w) {
        height = h;
        width = w;
        try {
            image = new Image(string);
        } catch (SlickException ex) {
            Logger.getLogger(AbstractActor.class.getName()).log(Level.SEVERE, null, ex);
        }
        setImageSize(height, width);
    }

    public void setImage(Image img) {
        image = img;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getX() {
        return xpos;
    }

    @Override
    public int getY() {
        return ypos;
    }

    @Override
    public void setX(int i) {
        xpos = i;
    }

    @Override
    public void setY(int i) {
        ypos = i;
    }

    @Override
    public abstract void act();

    @Override
    public void setWorld(World wrld) {
        world = wrld;
    }

    public World getWorld() {
        return world;
    }

    public void setImageSize(int h, int w) {
        image = image.getScaledCopy(height, width);
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rot) {
        rotation = rot;
    }

    public boolean intersects(AbstractActor me, AbstractActor actor) {
        if (actor != me) {
            if ((me.xpos + me.width >= actor.getX() && me.ypos + me.height >= actor.ypos) || (me.xpos <= actor.xpos + actor.width  && me.ypos <= actor.ypos + actor.height)) {
                return true;
            }
        }
        return false;
    }

}
