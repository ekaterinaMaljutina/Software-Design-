package ru.spbau.mit.moves.gamePoint;


import ru.spbau.mit.moves.map.WorldMap;

/**
 * Class which shows position on map
 */
public class Position {

    /**
     * Offset for Y-axis in order of {@link Movement} enum
     */
    private static final int[] MOVE_VALUE_Y = {-1, 1, 0, 0, 0, 0, 0};
    /**
     * Offset for X-axis in order of {@link Movement} enum
     */
    private static final int[] MOVE_VALUE_X = {0, 0, -1, 1, 0, 0, 0};

    private int x;
    private int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Changes position on map on given movement
     *
     * @param map      world's map
     * @param movement movement
     */
    public void move(WorldMap map, Movement movement) {
        int moveX = x + MOVE_VALUE_X[movement.ordinal()];
        int moveY = y + MOVE_VALUE_Y[movement.ordinal()];
        if (map.isEmptyCell(moveY, moveX)) {
            x = moveX;
            y = moveY;

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }
        return false;
    }
}
