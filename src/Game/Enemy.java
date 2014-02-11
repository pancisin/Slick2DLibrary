package Game;

import System.World;

/**
 *
 * @author Patrik
 */
public class Enemy extends AbstractActor {

    private int posun = 0;

    public Enemy(World wrld) {
        this.setImage("resources/images/planet0.png", 100, 100);
        this.getImage().setCenterOfRotation(this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
        this.setWorld(wrld);

    }

    @Override
    public void act() {
        this.setX(posun++);
        for (AbstractActor actor : this.getWorld().getList()) {
            if ((actor instanceof Package) && (actor.intersects(this, actor))) {

                this.getWorld().removeFromWorld(this);
                break;
            }
        }

    }

}
