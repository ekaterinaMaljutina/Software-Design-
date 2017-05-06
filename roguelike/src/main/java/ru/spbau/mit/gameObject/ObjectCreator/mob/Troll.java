package ru.spbau.mit.gameObject.ObjectCreator.mob;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Troll extends Mob {
    private static final int HEALTH = 50;
    private static final int ATTACK = 40;

    private static final Attributes attributes = new Attributes(HEALTH, ATTACK);

    public Troll(Position position) {
        super(position, attributes);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }

}
