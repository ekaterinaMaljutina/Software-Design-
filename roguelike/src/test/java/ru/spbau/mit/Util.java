package ru.spbau.mit;


import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Util {

    public static Creature createCreature(Position position, Attributes attributes) {
        return new Creature(position, attributes) {
            @Override
            public void accept(MoveObject moveObject) { }
        };
    }

}


