package ru.spbau.mit.moves.inputListener;


import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.map.WorldMap;


public interface Strategy {
    /**
     * Get movement from current position of given creature
     *
     * @param creature creature to be moved
     * @param worldMap world description
     * @return Movement
     */
    Movement getMove(Creature creature, WorldMap worldMap);
}
