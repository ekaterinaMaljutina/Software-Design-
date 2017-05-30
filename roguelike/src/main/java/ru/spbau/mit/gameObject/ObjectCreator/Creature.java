package ru.spbau.mit.gameObject.ObjectCreator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.map.WorldMap;


/**
 * Class for different creatures in our game(and for user's hero too).
 */
public abstract class Creature extends GameObject {

    private static final Logger LOGGER = LogManager.getLogger(Creature.class);

    public Creature(Position pos, Attributes attr) {
        super(pos, attr);
    }

    /**
     * Move on map to given movement
     *
     * @param map      world's map
     * @param movement movement
     */
    public void move(WorldMap map, Movement movement) {
        position.move(map, movement);
    }

    /**
     * Check is lives game object
     *
     * @return is object alive or not
     */
    public boolean isLived() {
        return attributes.getHealth() > 0;
    }

    /**
     * Attack another object
     *
     * @param creature
     */
    public void attack(Creature creature) {
        creature.getAttributes().subHealth(
                Math.max(0, attributes.getAttack() - creature.getAttributes().getDefense()));
    }

    /**
     * Applies given item to object
     *
     * @param item   item to be applied
     * @param active applying or canceling action after apply
     */
    public void applyItem(Item item, boolean active) {
        int val = -1;
        if (!active) {
            val = 1;
        }
        attributes.subHealth(item.getHealth() * val);
        attributes.subAttack(item.getAttack() * val);
        attributes.subDefense(item.getDefense() * val);

        LOGGER.info(String.format("Apply hero item %s; \n \t\t\t\t\t" +
                        "Item attributes: health = %s, attack = %s, defense = %s" +
                        "Current attribures: health = %s, attack = %s, defense = %s", item.getName(),
                item.getHealth(), item.getAttack(), item.getDefense(),
                attributes.getHealth(), attributes.getAttack(), attributes.getDefense()));
        item.setActive(active);
    }
}
