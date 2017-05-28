package ru.spbau.mit.gameObject.GameObject.Item;


import ru.spbau.mit.moves.gamePoint.Position;

import java.util.Random;

/**
 * Factory which creates items
 */
public class ItemFactory {

    public static enum TYPE {
        KIT,
        SHIELD
    };

    /**
     * Creates item on gives position
     * @param position position on the map where item will be created
     * @param type type of item for creating
     * @return created item
     */
    public static Item createItem(Position position, TYPE type) {
        switch (type) {
            case KIT:
                return new Kit(position);
            case SHIELD:
                return new Shield(position);
            default:
                throw new RuntimeException("item not found");
        }
    }

    /**
     * Creates item on gives position
     * @param position position position on the map where item will be created
     * @param random random for created type of item
     * @return created item
     */
    public static Item createRandomItem(Position position, Random random) {
        return createItem(position, TYPE.values()[random.nextInt(TYPE.values().length)]);
    }
}
