package ru.spbau.mit.gameObject.ObjectCreator.mob;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Position;

/**
 * Base class for any mobs
 */
public abstract class Mob extends Creature {
    public Mob(Position position, Attributes attributes) {
        super(position, attributes);
    }
}
