package Game;

import static java.lang.Math.*;

/**
 *
 * @author Patrik
 */
public class Package extends AbstractActor {

    AbstractActor actor;
    int c = 10;
    private final double degree;

    public Package(AbstractActor act) {
        actor = act;
        this.setImage("resources/images/package.png", 40, 40);
        this.setPosition(actor.getX() + (int) actor.getImage().getCenterOfRotationX(), actor.getY() + (int) actor.getImage().getCenterOfRotationY());

        degree = actor.getRotation();
    }

    @Override
    public void act() {
        this.setX((int) (this.getX() + cos(toRadians(degree)) * c));
        this.setY((int) (this.getY() + sin(toRadians(degree)) * c));
    }
}
