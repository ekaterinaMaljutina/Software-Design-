package ru.spbau.mit.gameObject.ObjectCreator.hero;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Hero extends Creature {

    private static final int HEALTH = 100;
    private static final int ATTACK = 10;

    private static final Attributes attributes = new Attributes(HEALTH, ATTACK);

    public Hero(Position position) {
        super(position, attributes);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }
}

