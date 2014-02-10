package System;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 *
 * @author Patrik
 */
public class SystemKey {

    Input input;
    GameContainer gcdef;

    public SystemKey(GameContainer gc) {
        input = gc.getInput(); 
        gcdef = gc;
    }

    public void update() {
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            gcdef.exit();
        }
        
    }
}