package ru.spbau.mit.ui;

import asciiPanel.AsciiPanel;
import com.sun.istack.internal.NotNull;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Mob;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Orc;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Troll;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.map.WorldMap;

import java.awt.*;

public class DrawMap implements MoveObject {

    private static final char BUSY_CELL = '#';
    private static final char EMPTY_CELL = '.';

    private static final char HERO = '@';
    private static final char TROLL = '&';
    private static final char ORCL = '*';

    private static final Color BUSY_COLOR = Color.DARK_GRAY;
    private static final Color EMPTY_COLOR = Color.WHITE;

    private static final Color HERO_COLOR = Color.MAGENTA;
    private static final Color TROLL_COLOR = Color.BLUE;
    private static final Color ORCL_COLOR = Color.CYAN;

    private static final int START_HERO_PORITION_X = 0;
    private static final int START_HERO_PORITION_Y = 3;

    private final int height;
    private final int weight;

    private char[][] startMap;
    private Color[][] startMapColor;

    private char[][] currentMap;
    private Color[][] currentMapColor;

    public DrawMap(WorldMap map) {
        height = map.getHeight();
        weight = map.getWeight();

        startMap = new char[height][weight];
        startMapColor = new Color[height][weight];

        currentMap = new char[height][weight];
        currentMapColor = new Color[height][weight];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
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
            for (int j = 0; j < weight; j++) {
                currentMap[i][j] = startMap[i][j];
                currentMapColor[i][j] = startMapColor[i][j];
            }
        }
    }

    public void drawToPanel(AsciiPanel panel) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
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
        drawPosition(mob.getPosition().getX(), mob.getPosition().getY(), ORCL, ORCL_COLOR);
    }

    private void drawPosition(int x, int y, char symbolType, Color colorSymbol) {
        currentMap[y][x] = symbolType;
        currentMapColor[y][x] = colorSymbol;
    }

    public static Position getHeroPosition() {
        return new Position(START_HERO_PORITION_X, START_HERO_PORITION_Y);
    }
}
