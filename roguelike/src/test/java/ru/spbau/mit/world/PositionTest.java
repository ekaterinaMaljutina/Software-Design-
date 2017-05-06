package ru.spbau.mit.world;

import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.map.WorldMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PositionTest {
    @Test
    public void moveTest() {
        WorldMap map = mock(WorldMap.class);

        when(map.isEmptyCell(0, 1)).thenReturn(false);
        when(map.isEmptyCell(0, 2)).thenReturn(false);
        when(map.isEmptyCell(1, 0)).thenReturn(true);
        when(map.isEmptyCell(1, 2)).thenReturn((true));

        Position position = new Position(1, 1);
        position.move(map, Movement.LEFT);
        Assert.assertEquals(new Position(1, 1), position);

        position.move(map, Movement.RIHGT);
        Assert.assertEquals(new Position(1, 1), position);

        position.move(map, Movement.UP);
        Assert.assertEquals(new Position(0, 1), position);

        position.move(map, Movement.DOWN);
        Assert.assertEquals(new Position(0, 1), position);

    }
}
