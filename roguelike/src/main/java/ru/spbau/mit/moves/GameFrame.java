package ru.spbau.mit.moves;


import asciiPanel.AsciiPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.game.Game;
import ru.spbau.mit.game.World;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.map.WorldMap;
import ru.spbau.mit.ui.DrawMap;

import javax.swing.*;
import java.util.List;

/**
 * Main frame with all game
 */
public class GameFrame extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(GameFrame.class);
    private static final String PATH_TO_MAP = "./src/main/resources/Map.txt";
    private static final int WINDOWS_WIDTH = 400;
    private static final int WINDOWS_HEIGHT = 500;

    private static final String HEALTH = "Health : ";
    private static final String ATTACK = "Attack : ";
    private static final String DEFENSE = "Defense : ";
    private static final String INVENTORY = "Inventory : ";

    private static final char ACTIVE = '+';
    private static final char NOT_ACTIVE = '-';

    private static final int OFFSET = 10;

    private AsciiPanel panel;
    private DrawMap drawMap;
    private WorldMap map;
    private final int w;
    private final int h;

    public GameFrame(Game game) {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            map = new WorldMap(PATH_TO_MAP);
            w = map.getWidth();
            h = map.getHeight();
            drawMap = new DrawMap(map);
        } catch (RuntimeException ex) {
            throw new RuntimeException();
        }
        panel = new AsciiPanel();
        setSize(WINDOWS_HEIGHT, WINDOWS_WIDTH);
        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);

        updataDrawGame(game.getWorld());
        game.setRender(this::updataDrawGame);

        addKeyListener(game.getHeroListener());

        drawAttributes(game.getWorld().getHero());

        LOGGER.info("Create game frame");
    }

    /**
     * Draws whole world
     *
     * @param world world to be drawn
     */
    private void updataDrawGame(World world) {
        panel.clear();
        drawMap.clear();
        for (GameObject gameObject : world.getGameObjects()) {
            gameObject.accept(drawMap);
        }
        drawAttributes(world.getHero());
        drawInvetory(world.getHero());
        drawMap.drawToPanel(panel);
        panel.repaint();
    }

    /**
     * Draws hero's attributes
     *
     * @param hero hero
     */
    private void drawAttributes(Hero hero) {
        panel.setCursorPosition(map.getWidth() + OFFSET, 0);
        panel.write(HEALTH + hero.getAttributes().getHealth());
        panel.setCursorPosition(map.getWidth() + OFFSET, 1);
        panel.write(ATTACK + hero.getAttack());
        panel.setCursorPosition(map.getWidth() + OFFSET, 2);
        panel.write(DEFENSE + hero.getDefense());
    }

    /**
     * Draw's hero's invetory
     *
     * @param hero hero
     */
    private void drawInvetory(Hero hero) {
        int xCursorPosition = map.getWidth() + OFFSET;
        int yCursorPosition = 5;
        panel.setCursorPosition(xCursorPosition, yCursorPosition);
        panel.write(INVENTORY);
        yCursorPosition++;
        panel.setCursorPosition(xCursorPosition, yCursorPosition);
        panel.write("# Name  Health Att  Def  Use");
        yCursorPosition++;
        List<Item> items = hero.getInventory();

        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);

            panel.setCursorPosition(xCursorPosition, yCursorPosition + i);
            panel.write(String.valueOf(i + 1));

            panel.setCursorPosition(xCursorPosition + 2, yCursorPosition + i);
            panel.write(currentItem.getName());

            panel.setCursorPosition(xCursorPosition + 12, yCursorPosition + i);
            panel.write(String.valueOf(currentItem.getAttributes().getHealth()));

            panel.setCursorPosition(xCursorPosition + 15, yCursorPosition + i);
            panel.write(String.valueOf(currentItem.getAttributes().getAttack()));

            panel.setCursorPosition(xCursorPosition + 20, yCursorPosition + i);
            panel.write(String.valueOf(currentItem.getAttributes().getDefense()));

            panel.setCursorPosition(xCursorPosition + 25, yCursorPosition + i);
            panel.write(currentItem.getActive() ? ACTIVE : NOT_ACTIVE);

        }
    }
}
