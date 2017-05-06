package ru.spbau.mit.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Mob;
import ru.spbau.mit.gameObject.ObjectCreator.mob.MobFactory;
import ru.spbau.mit.moves.gamePoint.Movement;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.moves.inputListener.Strategy;
import ru.spbau.mit.moves.map.WorldMap;
import ru.spbau.mit.ui.DrawMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private static final Logger LOGGER = LogManager.getLogger(World.class);
    private static final int COUNT_MOBS = 20;

    private Random random = new Random();
    private WorldMap map;
    private Hero hero;

    private MobFactory mobFactory = new MobFactory();
    private List<Mob> mob = new ArrayList<>();
    private List<Position> emptyPositions;

    public World(WorldMap map) {
        this.map = map;
        hero = new Hero(DrawMap.getHeroPosition());
        emptyPositions = map.getEmptyCell();

        for (int i = 0; i < COUNT_MOBS; i++) {
            mob.add(createRandomMob());
        }
    }

    public void turn(Strategy strategyHero, Strategy strategyMob) {
        Movement heroCurrentMove = strategyHero.getMove(hero, map);
        if (heroCurrentMove != null && heroCurrentMove != Movement.NONE) {
            hero.move(map, heroCurrentMove);
        }
        List<Movement> movementsMob = new ArrayList<>();

        for (Mob currentMob : mob) {
            movementsMob.add(strategyMob.getMove(currentMob, map));
        }
        for (int i = 0; i < mob.size(); i++) {
            mob.get(i).move(map, movementsMob.get(i));
        }
        intesectionHeroWithMob();
    }

    public Hero getHero() {
        return hero;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(hero);
        for (Mob currentMob : mob) {
            gameObjects.add(currentMob);
        }
        return gameObjects;
    }

    private Mob createRandomMob() {
        List<Position> currentEmptyPos = findEmptyPositions();
        if (!currentEmptyPos.isEmpty()) {
            Position position = currentEmptyPos.get(random.nextInt(currentEmptyPos.size()));
            return mobFactory.createRandom(position);
        }
        LOGGER.error("empty cell not found for create mod");
        throw new RuntimeException("empty cell not found for create mod");

    }

    private List<Position> findEmptyPositions() {
        List<Position> currentEmptyPositions = new ArrayList<>();

        for (Position position : emptyPositions) {
            boolean flagForCurrentPosition = true;
            if (position.equals(hero.getPosition())) {
                flagForCurrentPosition = false;
            }
            for (Mob currentMob : mob) {
                if (currentMob.getPosition().equals(position)) {
                    flagForCurrentPosition = false;
                }
            }

            if (flagForCurrentPosition) {
                currentEmptyPositions.add(position);
            }
        }
        return currentEmptyPositions;
    }

    private void intesectionHeroWithMob() {
        List<Mob> killedMob = new ArrayList<>();

        for (Mob currnetMob : mob) {
            if (hero.getPosition().equals(currnetMob.getPosition())) {
                LOGGER.info(String.format("%s with healf = %s  attack hero ",
                        currnetMob.getClass().getName(),
                        hero.getHealf()));
                hero.attack(currnetMob);
                LOGGER.info(String.format("after attact  %s with healf = %s",
                        currnetMob.getClass().getName(),
                        currnetMob.getHealf()));
                LOGGER.info(String.format("after attact hero healf = %s", hero.getHealf()));
                currnetMob.attack(hero);
                LOGGER.info(String.format("%s attack hero ", currnetMob.getClass().getName()));
                if (!currnetMob.isLived()) {
                    killedMob.add(currnetMob);
                }
            }
        }

        for (Mob killedCurrentMob : killedMob) {
            LOGGER.info(String.format("KILL MOB : %s", killedCurrentMob.getClass().getName()));
            mob.remove(killedCurrentMob);
        }
    }
}
