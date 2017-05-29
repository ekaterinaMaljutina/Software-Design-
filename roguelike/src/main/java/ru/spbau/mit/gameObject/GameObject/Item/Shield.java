package ru.spbau.mit.gameObject.GameObject.Item;


import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Shield extends Item {
    /**
     * Shield attributes
     */
    public static final String NAME = "Shield";
    private static final int HEALTH = 0;
    public static final int ATTACK = 0;
    public static final int DEFENSE = 1;
    public static final Attributes ATTRIBUTES = new Attributes(HEALTH, ATTACK, DEFENSE);


    public Shield(Position position) {
        super(position, ATTRIBUTES, NAME);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }
}
