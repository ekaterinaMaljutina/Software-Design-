package ru.spbau.mit.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.moves.inputListener.KeyBoardInputListener;
import ru.spbau.mit.moves.inputListener.MobRandomStrategy;
import ru.spbau.mit.moves.map.WorldMap;

import java.util.function.Consumer;

/**
 * Class with implantations game logic( have waits movement while hero is alive)
 */
public class Game {

    private static final Logger LOGGER = LogManager.getLogger(Game.class);
    private static final String PATH_TO_MAP = "./src/main/resources/Map.txt";

    private World world;
    private KeyBoardInputListener strategy = new KeyBoardInputListener();
    private MobRandomStrategy mobRandomStrategy = new MobRandomStrategy();
    /**
     * Callback for drawing
     */
    private Consumer<World> render = (world) -> {
    };

    public Game() {
        world = new World(new WorldMap(PATH_TO_MAP));
    }

    /**
     * Run game
     */
    public void run() {
        while (world.getHero().isLived()) {
            world.turn(strategy, mobRandomStrategy);
            render.accept(world);
        }
        LOGGER.info("HERO KILLED");
    }

    public KeyBoardInputListener getHeroListener() {
        return strategy;
    }

    public void setRender(Consumer<World> render) {
        this.render = render;
    }

    public World getWorld() {
        return world;
    }
}
