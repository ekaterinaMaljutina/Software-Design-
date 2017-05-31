package ru.spbau.mit.gameObject.GameObject.Item;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.moves.gamePoint.Position;

/**
 * Base class for any item
 */
abstract public class Item extends GameObject {
    /**
     * Name of item
     */
    protected String nameItem;
    /**
     * Flag shows whether item is activated at the moment
     */
    protected boolean active = false;

    public Item(Position position, Attributes attribute, String name) {
        super(position, attribute);
        nameItem = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    public String getName() {
        return nameItem;
    }

}
