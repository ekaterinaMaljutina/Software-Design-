package ru.spbau.mit;


import javafx.geometry.Pos;
import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

public class Util {
    private static final String NAME_ITEM = "";

    public static Creature createCreature(Position position, Attributes attributes) {
        return new Creature(position, attributes) {
            @Override
            public void accept(MoveObject moveObject) { }
        };
    }

    public static Item createItem(Position position, Attributes attributes) {
        return new Item(position, attributes, NAME_ITEM) {
            @Override
            public void accept(MoveObject moveObject) {}
        };
    }

}


