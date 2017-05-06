package ru.spbau.mit.moves.inputListener;

import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.map.WorldMap;

import java.util.Random;

public class MobRandomStrategy implements Strategy {

    private static Random random = new Random();

    @Override
    public Movement getMove(Creature creature, WorldMap worldMap) {
        Movement[] allMoves = Movement.values();
        return allMoves[random.nextInt(allMoves.length)];
    }
}

