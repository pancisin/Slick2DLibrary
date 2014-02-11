package Game;

import System.World;
import static java.lang.Math.abs;
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
    private static final double maxfallspeed = 10;
    private static final double gravity = 1.01;
    private double yspeed = 0;
    private double yacceleration = 0.1;
    private boolean jumping = true;
    //   private boolean falling = true;

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
            if ((me.xpos + me.width >= actor.getX() && (actor.getX() + actor.width >= me.xpos))) {
                if ((me.ypos + me.height >= actor.getY()) && (actor.getY() + actor.height >= me.getY())) {
                    return true;
                }
            }

        }
        return false;

    }

    public void applyGravity() {
        if (jumping) {
            if ((this.yspeed <= maxfallspeed)) {
                this.yacceleration = this.yacceleration * gravity;
                this.yspeed = this.yspeed + this.yacceleration;
            }

            this.ypos = this.ypos + (int) this.yspeed;

        }

        if (this.ypos > 600) {
            jumping = false;
        }

    }

    public void jump() {
        this.yspeed = -10;
        this.yacceleration = 0.1;
        this.jumping = true;
    }

    public void setJumping(Boolean b) {
        this.jumping = b;
    }

}
