package ru.spbau.mit.world;


import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.moves.map.WorldMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WorldMapTest {
    private static final int H = 3;
    private static final int W = 3;

    private static final String[] CELLS = {
            "..#",
            "#.#",
            "..#"
    };


    @Test
    public void parseTest() throws IOException {
        String name = "./src/test/resources/map.txt";
        FileWriter file = new FileWriter(name);
        BufferedWriter bufferedWriter = new BufferedWriter(file);
        bufferedWriter.write(H + " " + W + "\n");
        for (int i = 0; i < H; i++) {
            bufferedWriter.write(CELLS[i] + "\n");
        }
        bufferedWriter.close();
        file.close();

        WorldMap map = new WorldMap(name);

        Assert.assertEquals(H, map.getHeight());
        Assert.assertEquals(W, map.getWidth());

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Assert.assertEquals(CELLS[i].charAt(j) == '.', map.isEmptyCell(j, i));
            }
        }
    }

}
