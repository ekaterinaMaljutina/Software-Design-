package ru.spbau.mit.gameObject.GameObject;

import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;


public abstract class GameObject {

    protected Position position;

    protected Attributes attributes;

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

    public int getHealf() {
        return attributes.getHealf();
    }

    public int getAttack() {
        return attributes.getAttack();
    }

    public abstract void accept(MoveObject moveObject);
}
