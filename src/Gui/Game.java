package Gui;

import Game.AbstractActor;
import Game.Actor;
import Game.Enemy;
import Game.Hero;
import System.Map;
import System.SystemKey;
import System.World;
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
    private Map map;

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

        map = new Map("resources/levels/untitled.tmx");

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

//        render mapy;
        map.render(0, 0);

//        render objektov;
        for (AbstractActor actual : world.getList()) {
            if (world.hasNext()) {
                if (actual.getImage() != null) {
                    g.drawImage(actual.getImage(), actual.getX(), actual.getY());
                }
            }
        }

//        render napis (to iba na skusku);
        g.drawString("PlanetExpress", player.getX() + 40, player.getY() - 20);
    }

    public Input keyListen() {
        return gcdef.getInput();
    }

    public GameContainer getGameContainer() {
        return gcdef;
    }

}
