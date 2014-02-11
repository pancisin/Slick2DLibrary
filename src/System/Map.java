package System;

import Game.AbstractActor;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Patrik
 */
public class Map {

    private final TiledMap tilMap;
    private int x, y;

    public Map(String f) throws SlickException {
        tilMap = new TiledMap(f);

       
    }

    public void render(int i, int j) {
        tilMap.render(i, j);
    }

    public void centerOn(AbstractActor actor) {

    }

}
