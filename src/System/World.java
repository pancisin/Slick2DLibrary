package System;

import Game.AbstractActor;

import java.util.ArrayList;

/**
 *
 * @author Patrik
 */
public class World {

    private int i, amount;
    ArrayList<AbstractActor> actorlist;
    private boolean adding;

    public World() {
        actorlist = new ArrayList<>();
        amount = 0;
        i = 0;
        adding = false;
    }

    public void addToWorld(AbstractActor actor) {
        adding = true;
        actorlist.add(actor);
        amount++;
    }

    public AbstractActor getNext() {
        if (actorlist.isEmpty()) {
            return null;
        }

        if (!hasNext()) {
            i = 0;
        }

        return (AbstractActor) actorlist.get(i++);
    }

    public void removeFromWorld(AbstractActor actor) {
        actorlist.remove(actor);
    }

    public boolean hasNext() {
        if (i < amount) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<AbstractActor> getList() {
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
