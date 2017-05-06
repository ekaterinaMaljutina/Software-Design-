package ru.spbau.mit.gameObject.ObjectCreator;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.map.WorldMap;


public abstract class Creature extends GameObject {

    public Creature(Position pos, Attributes attr) {
        super(pos, attr);
    }

    public void move(WorldMap map, Movement movement) {
        position.move(map, movement);
    }

    public boolean isLived() {
        return attributes.getHealf() > 0;
    }

    public void attack(Creature creature) {
        creature.getAttributes().subHealf(attributes.getAttack());
    }

}
