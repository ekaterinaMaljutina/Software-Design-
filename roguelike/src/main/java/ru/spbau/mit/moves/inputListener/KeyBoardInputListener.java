package ru.spbau.mit.moves.inputListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.map.WorldMap;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Input controller for keyboard
 */

public class KeyBoardInputListener extends KeyAdapter implements Strategy {

    private static final Logger LOGGER = LogManager.getLogger(KeyBoardInputListener.class);
    private static final Map<String, Movement> KEY_TO_MOVEMENT = new HashMap<String, Movement>() {{
        put("Down", Movement.DOWN);
        put("Up", Movement.UP);
        put("Left", Movement.LEFT);
        put("Right", Movement.RIHGT);
        put("W", Movement.TASK_OFF);
    }};

    private static final String KEY_TEXT = "keyText";
    /**
     * Stores not yet movement events
     */
    private AbstractQueue<Movement> queueMoves = new PriorityQueue<>();

    /**
     * Handels key event
     *
     * @param e key event to handle
     */
    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (queueMoves) {
            List<String> splitString = Arrays.asList(e.paramString().split(","));
            Map<String, String> pressKeyArgs = splitString
                    .stream()
                    .map(s -> s.split("="))
                    .filter(strings -> strings.length == 2)
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
            if (KEY_TO_MOVEMENT.containsKey(pressKeyArgs.get(KEY_TEXT))) {
                queueMoves.add(KEY_TO_MOVEMENT.get(pressKeyArgs.get(KEY_TEXT)));
                queueMoves.notify();
            }
        }
    }

    @Override
    public Movement getMove(Creature creature, WorldMap worldMap) {
        Hero hero = (Hero) creature;
        synchronized (queueMoves) {
            while (true) {
                while (queueMoves.isEmpty()) {
                    try {
                        queueMoves.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                Movement currentMove = queueMoves.remove();
                LOGGER.info(String.format("MOVE HERO to %s", currentMove));
                if (currentMove != null) {
                    return currentMove;
                }
            }
        }
    }
}
