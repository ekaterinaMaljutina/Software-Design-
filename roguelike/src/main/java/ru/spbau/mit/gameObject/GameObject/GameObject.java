package ru.spbau.mit.gameObject.GameObject;

import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;


/**
 * Base class for game object
 */
public abstract class GameObject {

    /**
     * Position of object on map
     */
    protected Position position;

    protected Attributes attributes;

    /**
     * Creates game object on given position and attributes
     *
     * @param position   position on map where create object
     * @param attributes attributes of creature
     */
    public GameObject(Position position, Attributes attributes) {
        this.position = position;
        this.attributes = attributes;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Position getPosition() {
        return position;
    }

    public int getHealth() {
        return attributes.getHealth();
    }

    public int getAttack() {
        return attributes.getAttack();
    }

    public int getDefense() {
        return attributes.getDefense();
    }

    /**
     * Method for "visitor" pattern
     *
     * @param moveObject current object
     */
    public abstract void accept(MoveObject moveObject);
}
