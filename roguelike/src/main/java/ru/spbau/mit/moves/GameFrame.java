package ru.spbau.mit.moves;


import asciiPanel.AsciiPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.game.Game;
import ru.spbau.mit.game.World;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.map.WorldMap;
import ru.spbau.mit.ui.DrawMap;

import javax.swing.*;

public class GameFrame extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(GameFrame.class);
    private static final String PATH_TO_MAP = "./src/main/resources/Map.txt";
    private static final int WINDOWS_Weight = 300;
    private static final int WINDOWS_Height = 400;

    private static final String HEALF = "Healf : ";
    private static final String ATTACK = "Attack : ";

    private AsciiPanel panel;
    private DrawMap drawMap;
    private WorldMap map;
    private final int w;
    private final int h;

    public GameFrame(Game game) throws RuntimeException {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            map = new WorldMap(PATH_TO_MAP);
            w = map.getWeight();
            h = map.getHeight();
            drawMap = new DrawMap(map);
        } catch (RuntimeException ex) {
            throw new RuntimeException();
        }
        panel = new AsciiPanel();
        setSize(WINDOWS_Height, WINDOWS_Weight);
        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);

        updataDrawGame(game.getWorld());
        game.serRender(this::updataDrawGame);

        addKeyListener(game.getHeroListner());

        drawAttributes(game.getWorld().getHero());

        LOGGER.info("Create game frame");
    }

    private void updataDrawGame(World world) {
        panel.clear();
        drawMap.clear();
        for (GameObject gameObject : world.getGameObjects()) {
            gameObject.accept(drawMap);
        }
        drawAttributes(world.getHero());
        drawMap.drawToPanel(panel);
        panel.repaint();
    }

    private void drawAttributes(Hero hero) {
        panel.setCursorPosition(map.getWeight() + 10, 0);
        panel.write(HEALF + hero.getAttributes().getHealf());
        panel.setCursorPosition(map.getWeight() + 10, 1);
        panel.write(ATTACK + hero.getAttack());
    }
}
