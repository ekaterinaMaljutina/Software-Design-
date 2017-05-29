package ru.spbau.mit.gameObject.ObjectCreator.mob;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.moves.gamePoint.Position;

import java.util.Random;

/**
 * Factory for creates mobs
 */
public class MobFactory {
    private static final Logger LOGGER = LogManager.getLogger(MobFactory.class);

    private static Random random = new Random();

    /**
     * Greate mob on given position
     *
     * @param position position on map where mob will be created
     * @param mobType  type of mob
     * @return created mob
     */
    public static Mob create(Position position, MobType mobType) {
        switch (mobType) {
            case ORC:
                return new Orc(position);
            case TROLL:
                return new Troll(position);
            default:
                LOGGER.error(String.format("Mob type not found  %s", mobType));
                throw new RuntimeException(String.format("Mob type not found  %s", mobType));
        }
    }

    /**
     * Creates random mob on given position
     *
     * @param position position on map where mob will be created
     * @return created mob
     */
    public static Mob createRandom(Position position) {
        return create(position, MobType.values()[random.nextInt(MobType.values().length)]);
    }
}
