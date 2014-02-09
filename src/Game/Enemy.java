package Game;

import System.World;

/**
 *
 * @author Patrik
 */
public class Enemy extends AbstractActor {

    private int posun = 0;
        
    public Enemy(World wrld) {
        this.setImage("resources/images/planet0.png");
        this.getImage().setCenterOfRotation(this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
        this.setImageSize(300, 300);
        this.setWorld(wrld);
        this.setPosition(50, 150);
        
    }

    @Override
    public void act() {
        this.setX(posun++);
    }

}
