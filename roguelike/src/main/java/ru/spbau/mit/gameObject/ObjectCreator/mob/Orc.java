package ru.spbau.mit.gameObject.ObjectCreator.mob;


import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Orc extends Mob {
    private static final int HEALTH = 30;
    private static final int ATTACK = 30;

    private static final Attributes attributes = new Attributes(HEALTH, ATTACK);

    public Orc(Position position) {
        super(position, attributes);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }
}
