package System;

import Game.Actor;
import java.util.ArrayList;

/**
 *
 * @author Patrik
 */
public class World {

    private int i, amount;
    ArrayList<Actor> actorlist;
    private boolean adding;

    public World() {
        actorlist = new ArrayList<>();
        amount = 0;
        i = 0;
        adding = false;
    }

    public void addToWorld(Actor actor) {
        adding = true;
        actorlist.add(actor);
        amount++;
    }

    public Actor getNext() {
        if (actorlist.isEmpty()) {
            return null;
        }

        if (!hasNext()) {
            i = 0;
        }

        return (Actor) actorlist.get(i++);
    }

    public void removeFromWorld(Actor actor) {
        actorlist.remove(actor);
    }

    public boolean hasNext() {
        if (i < amount) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Actor> getList() {
        return actorlist;
    }

    public void setModificationException() {
        adding = false;
    }

    public boolean getModificationException() {
        return adding;
    }
    
    public int getAmount() {
        return amount;
    }
}
