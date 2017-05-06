package ru.spbau.mit.moves.inputListener;


import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.map.WorldMap;

public interface Strategy {
    Movement getMove(Creature creature, WorldMap worldMap);
}
