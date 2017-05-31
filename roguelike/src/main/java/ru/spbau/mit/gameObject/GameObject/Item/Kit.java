package ru.spbau.mit.gameObject.GameObject.Item;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Kit extends Item {
    /**
     * Kit attributes
     */
    private static final String NAME = "Kit";
    private static final int HEALTH = 5;
    private static final int ATTACK = 0;
    private static final int DEFENSE = 0;
    private static final Attributes ATTRIBUTES = new Attributes(HEALTH, ATTACK, DEFENSE);


    public Kit(Position position) {
        super(position, ATTRIBUTES, NAME);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }
}
