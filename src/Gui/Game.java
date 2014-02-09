package Gui;

import Game.Actor;
import Game.Enemy;
import Game.Hero;
import System.Map;
import System.SystemKey;
import System.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Patrik
 */
public class Game extends BasicGame {

    private GameContainer gcdef;
    private SystemKey sk;
    World world;
    private Hero player;

    public Game(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        gcdef = gc;
        sk = new SystemKey(gc);
        world = new World();

        player = new Hero(gc, world);
        world.addToWorld(player);

        Enemy enemy = new Enemy(world);
        world.addToWorld(enemy);
        
//        Map map = null;
//        try {
//            map = new Map("resources/levels/thefirst.txt");
//        } catch (IOException ex) {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        world.addToWorld(map);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        sk.update();
        for (Actor actual : world.getList()) {
            actual.act();
            if (world.getModificationException()) {
                world.setModificationException();
                break;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (Actor actual : world.getList()) {
            if (world.hasNext()) {
                if (actual.getImage() != null)  {
                    g.drawImage(actual.getImage(), actual.getX(), actual.getY());
                }
            }
        }
        g.drawString("PlanetExpress", player.getX() + 40, player.getY() - 20);
    }

    public Input keyListen() {
        return gcdef.getInput();
    }

    public GameContainer getGameContainer() {
        return gcdef;
    }
    
}
