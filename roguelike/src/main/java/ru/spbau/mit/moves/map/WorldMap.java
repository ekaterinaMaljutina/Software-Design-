package ru.spbau.mit.moves.map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.moves.gamePoint.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorldMap {

    private static final Logger LOGGER = LogManager.getLogger(WorldMap.class);
    private static final char EMPTY_CELL = '.';
    private static final char BUSY_CELL = '#';

    private int w;
    private int h;
    private CellType[][] map;


    public WorldMap(String fileName) throws RuntimeException {
        Scanner scannerFile;
        try {
            scannerFile = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            LOGGER.fatal(String.format("file %s not fount", fileName));
            throw new RuntimeException("file not found");
        }
        w = scannerFile.nextInt();
        h = scannerFile.nextInt();
        scannerFile.nextLine();
        map = new CellType[h][w];
        for (int i = 0; i < h; i++) {
            String lineOfFile = scannerFile.nextLine();
            for (int j = 0; j < w; j++) {
                switch (lineOfFile.charAt(j)) {
                    case EMPTY_CELL:
                        map[i][j] = CellType.EMPTY;
                        break;
                    case BUSY_CELL:
                        map[i][j] = CellType.BUSE;
                        break;
                    default:
                        LOGGER.error("simbol in map not recognized");
                        throw new RuntimeException("simbol in map not recognized");
                }
            }
        }
    }

    public int getWeight() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public boolean isEmptyCell(int x, int y) {
        return x < w && x >= 0 && y < h && y >= 0 && map[y][x] == CellType.EMPTY;
    }

    public List<Position> getEmptyCell() {
        List<Position> emptyPositions = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == CellType.EMPTY) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }
        return emptyPositions;
    }

}
