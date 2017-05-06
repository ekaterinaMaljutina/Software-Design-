package ru.spbau.mit.gameObject.ObjectCreator.mob;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.moves.GameFrame;
import ru.spbau.mit.moves.gamePoint.Position;

import java.util.Random;

public class MobFactory {
    private static final Logger LOGGER = LogManager.getLogger(MobFactory.class);

    private Random random = new Random();

    public Mob create(Position position, MobType mobType) {
        switch (mobType) {
            case ORCL:
                return new Orc(position);
            case TROLL:
                return new Troll(position);
            default:
                LOGGER.error(String.format("Mob type not found  %s", mobType));
                throw new RuntimeException(String.format("Mob type not found  %s", mobType));
        }
    }

    public Mob createRandom(Position position) {
        return create(position, MobType.values()[random.nextInt(MobType.values().length)]);
    }
}
