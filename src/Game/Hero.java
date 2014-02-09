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
    private int direction;

    public Hero(GameContainer gcc, World wrld) throws SlickException {
        gc = gcc;
        this.setImage("resources/images/player.png");
        this.getImage().setCenterOfRotation(this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
        this.setWorld(wrld);
        i = 0;
    }

    @Override
    public void act() {
//        Pohyb
        double acceleration = 0.05;

        input = gc.getInput();

        if ((input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_A) || input.isKeyPressed(Input.KEY_D))) {
            i = 0;
        }

        if (i > 0 && !(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D))) {
            do {
                switch (direction) {
                    case 1:
                        moveY(-1);
                        break;
                    case 2:
                        moveY(1);
                        break;
                    case 3:
                        moveX(-1);
                        break;
                    case 4:
                        moveX(1);
                        break;
                    default :
                        break;
                }

                i -= acceleration;
            } while (i == 0);
        }

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)) {
            if (i < 2) {
                i += acceleration;
            }
        }

        if (input.isKeyDown(Input.KEY_W)) {
            moveY(-1);
            direction = 1;
        }
        if (input.isKeyDown(Input.KEY_S)) {
            direction = 2;
            moveY(1);
        }
        if (input.isKeyDown(Input.KEY_A)) {
            direction = 3;
            moveX(-1);
        }
        if (input.isKeyDown(Input.KEY_D)) {
            direction = 4;
            moveX(1);
        }

//       Otočenie 
        if (input.getMouseX() > this.getX()) {
            this.setImage("resources/images/player.png");
        } else {
            this.setImage("resources/images/playerev.png");
        }

//        Rotácia
        float yDistance = input.getMouseY() - this.getY();
        float xDistance = input.getMouseX() - this.getX();

        this.setRotation(Math.toDegrees(Math.atan2(yDistance, xDistance)));
        this.getImage().setRotation((float) this.getRotation());

        //        Funkčné tlačidlo (mouse button 1/2)
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Package bullet = new Package(this);
            this.getWorld().addToWorld(bullet);
        }
    }

    public void moveX(int koef) {
        this.setX((int) (this.getX() + koef * exp(i)));
    }

    public void moveY(int koef) {
        this.setY((int) (this.getY() + koef * exp(i)));
    }
}
