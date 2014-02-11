package Game;

import System.World;
import static java.lang.Math.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Patrik
 */
public class Hero extends AbstractActor {

    private final GameContainer gc;
    private Input input;
    private float i;

    private enum move {

        up,
        left,
        down,
        right
    };
    private move direction;
    private static final double acceleration = 0.05;
    int d = 0;

    public Hero(GameContainer gcc, World wrld) throws SlickException {
        gc = gcc;
        this.setImage("resources/images/player.png", 253, 83);
        this.getImage().setCenterOfRotation(this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
        this.setWorld(wrld);
        i = 0;
    }

    @Override
    public void act() {
//        Pohyb
        input = gc.getInput();

        if ((input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_A) || input.isKeyPressed(Input.KEY_D))) {
            i = 0;
        }

        if (i > 0 && !(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D))) {
            do {
                switch (direction) {
                    case up:
                        moveY(-1);
                        break;
                    case down:
                        moveY(1);
                        break;
                    case left:
                        moveX(-1);
                        break;
                    case right:
                        moveX(1);
                        break;
                    default:
                        break;
                }

                i -= acceleration / 2;
            } while (i == 0);
        }

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)) {
            if (i < 2) {
                i += acceleration;
            }
        }

        if (input.isKeyDown(Input.KEY_W)) {
            moveY(-1);
            direction = move.up;

        }
        if (input.isKeyDown(Input.KEY_S)) {
            moveY(1);
            direction = move.down;
        }
        if (input.isKeyDown(Input.KEY_A)) {
            moveX(-1);
            direction = move.left;
        }
        if (input.isKeyDown(Input.KEY_D)) {
            moveX(1);
            direction = move.right;
        }

//       Otočenie 
        if (input.getMouseX() > this.getX() + this.getImage().getCenterOfRotationX()) {
            if (!"resources/images/player.png".equals(this.getImage().getResourceReference())) {
                this.setImage("resources/images/player.png", 253, 83);
            }
        } else {
            if (!"resources/images/playerev.png".equals(this.getImage().getResourceReference())) {
                this.setImage("resources/images/playerev.png", 253, 83);
            }
        }

//        Rotácia
        float yDistance = input.getMouseY() - this.getY() - this.getImage().getCenterOfRotationY() ;
        float xDistance = input.getMouseX() - this.getX() - this.getImage().getCenterOfRotationX();

        this.setRotation(Math.toDegrees(Math.atan2(yDistance, xDistance)));
        this.getImage().setRotation((float) this.getRotation());

//        Funkčné tlačidlo (mouse button 1/2)
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Package bullet = new Package(this);
            this.getWorld().addToWorld(bullet);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            jump();
        }
        applyGravity();

    }

    private void moveX(int koef) {
        this.setX((int) (this.getX() + koef * exp(i)));
    }

    private void moveY(int koef) {
        this.setY((int) (this.getY() + koef * exp(i)));
    }
}
