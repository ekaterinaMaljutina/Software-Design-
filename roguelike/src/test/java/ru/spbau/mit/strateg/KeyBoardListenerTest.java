package ru.spbau.mit.strateg;


import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.inputListener.KeyBoardInputListener;
import ru.spbau.mit.moves.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.mockito.Mockito.mock;

public class KeyBoardListenerTest {

    @Test
    public void getMovesTest() {
        Hero hero = mock(Hero.class);
        WorldMap world = mock(WorldMap.class);

        KeyBoardInputListener inputListener = new KeyBoardInputListener();
        Button button = new Button();
        button.addKeyListener(inputListener);

        KeyEvent eventUp = new KeyEvent(button, 1, 20, 1, 38, '\uFFFF');
        inputListener.keyPressed(eventUp);
        Assert.assertEquals(Movement.UP, inputListener.getMove(hero, world));

        KeyEvent eventDown = new KeyEvent(button, 1, 20, 1, 40, '\uFFFF');
        KeyEvent eventLeft = new KeyEvent(button, 1, 20, 1, 37, '\uFFFF');

        inputListener.keyPressed(eventDown);
        inputListener.keyPressed(eventLeft);
        Assert.assertEquals(Movement.LEFT, inputListener.getMove(hero, world));
    }
}
