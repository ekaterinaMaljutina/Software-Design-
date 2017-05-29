package ru.spbau.mit.ui;

import asciiPanel.AsciiPanel;
import com.sun.istack.internal.NotNull;
import ru.spbau.mit.gameObject.GameObject.Item.Kit;
import ru.spbau.mit.gameObject.GameObject.Item.Shield;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Orc;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Troll;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.map.WorldMap;

import java.awt.*;

/**
 * Draw world's map
 */
public class DrawMap implements MoveObject {

    private static final char BUSY_CELL = '#';
    private static final char EMPTY_CELL = '.';

    private static final char HERO = '@';
    private static final char TROLL = '&';
    private static final char ORC = '*';
    private static final char KIT = '!';
    private static final char SHIELD = 'O';

    private static final Color BUSY_COLOR = Color.DARK_GRAY;
    private static final Color EMPTY_COLOR = Color.WHITE;

    private static final Color HERO_COLOR = Color.MAGENTA;
    private static final Color TROLL_COLOR = Color.BLUE;
    private static final Color ORC_COLOR = Color.CYAN;
    private static final Color KIT_COLOR = Color.RED;
    private static final Color SHIELD_COLOR = Color.YELLOW;

    private static final int START_HERO_POSITION_X = 0;
    private static final int START_HERO_POSITION_Y = 3;

    private final int height;
    private final int width;

    private char[][] startMap;
    private Color[][] startMapColor;

    private char[][] currentMap;
    private Color[][] currentMapColor;

    public DrawMap(WorldMap map) {
        this.height = map.getHeight();
        this.width = map.getWidth();

        startMap = new char[height][width];
        startMapColor = new Color[height][width];

        currentMap = new char[height][width];
        currentMapColor = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                startMap[i][j] = EMPTY_CELL;
                startMapColor[i][j] = EMPTY_COLOR;
                if (!map.isEmptyCell(i, j)) {
                    startMap[i][j] = BUSY_CELL;
                    startMapColor[i][j] = BUSY_COLOR;
                }
            }
        }
        clear();
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentMap[i][j] = startMap[i][j];
                currentMapColor[i][j] = startMapColor[i][j];
            }
        }
    }

    public void drawToPanel(AsciiPanel panel) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                panel.write(currentMap[i][j], i, j, currentMapColor[i][j]);
            }
        }
    }

    @Override
    public void move(@NotNull final Hero hero) {
        drawPosition(hero.getPosition().getX(), hero.getPosition().getY(), HERO, HERO_COLOR);
    }

    @Override
    public void move(@NotNull final Troll mob) {
        drawPosition(mob.getPosition().getX(), mob.getPosition().getY(), TROLL, TROLL_COLOR);
    }

    @Override
    public void move(@NotNull final Orc mob) {
        drawPosition(mob.getPosition().getX(), mob.getPosition().getY(), ORC, ORC_COLOR);
    }

    @Override
    public void move(@NotNull Kit kit) {
        drawPosition(kit.getPosition().getX(), kit.getPosition().getY(), KIT, KIT_COLOR);
    }

    @Override
    public void move(@NotNull Shield shield) {
        drawPosition(shield.getPosition().getX(), shield.getPosition().getY(), SHIELD, SHIELD_COLOR);
    }

    private void drawPosition(int x, int y, char symbolType, Color colorSymbol) {
        currentMap[y][x] = symbolType;
        currentMapColor[y][x] = colorSymbol;
    }

    public static Position getHeroPosition() {
        return new Position(START_HERO_POSITION_X, START_HERO_POSITION_Y);
    }
}
