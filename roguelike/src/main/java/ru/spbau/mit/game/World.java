package ru.spbau.mit.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.gameObject.GameObject.GameObject;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.GameObject.Item.ItemFactory;
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
    /**
     * Mobs count on map at the start
     */
    private static final int COUNT_MOBS = 10;

    /**
     * Items count on map at the start
     */
    private static final int COUNT_INVENTORY = 15;

    private Random random = new Random();

    private WorldMap map;
    private Hero hero;

    private MobFactory mobFactory = new MobFactory();

    private List<Mob> mob = new ArrayList<>();
    private List<Item> inventory = new ArrayList<>();
    private List<Position> emptyPositions;

    /**
     * Create new world to map
     * Generates hero, items, mobs
     *
     * @param map world's map
     */
    public World(WorldMap map) {
        this.map = map;
        hero = new Hero(DrawMap.getHeroPosition());
        emptyPositions = map.getEmptyCell();

        for (int i = 0; i < COUNT_MOBS; i++) {
            mob.add(createRandomMob());
        }

        for (int i = 0; i < COUNT_INVENTORY; i++) {
            inventory.add(createItem());
        }
    }

    /**
     * Make turn of game
     *
     * @param strategyHero strategy which hero is using.
     * @param strategyMob  strategy which mobs is using.
     */
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
        applyItem();
        intesectionHeroWithMob();
    }

    public Hero getHero() {
        return hero;
    }

    /**
     * Return all game objects
     *
     * @return list of all game object at current moment (hero, items, mobs)
     */
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(hero);
        for (Mob currentMob : mob) {
            gameObjects.add(currentMob);
        }

        for (Item item : inventory) {
            gameObjects.add(item);
        }
        return gameObjects;
    }

    /**
     * Generates mod on random empty cell
     *
     * @return mob
     */
    private Mob createRandomMob() {
        List<Position> currentEmptyPos = findEmptyPositions();
        if (!currentEmptyPos.isEmpty()) {
            Position position = currentEmptyPos.get(random.nextInt(currentEmptyPos.size()));
            return mobFactory.createRandom(position);
        }
        LOGGER.error("empty cell not found for create mod");
        throw new RuntimeException("empty cell not found for create mod");
    }


    /**
     * Find empty cell in map
     *
     * @return list of empty cell in the current moment
     */
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
            for (Item item : inventory) {
                if (item.getPosition().equals(position)) {
                    flagForCurrentPosition = false;
                }
            }

            if (flagForCurrentPosition) {
                currentEmptyPositions.add(position);
            }
        }
        return currentEmptyPositions;
    }

    /**
     * Generates item on random empty cell
     *
     * @return Item
     */
    private Item createItem() {
        List<Position> emptyPositionsList = findEmptyPositions();
        if (!emptyPositionsList.isEmpty()) {
            Position itemPosition = emptyPositionsList.get(random.nextInt(emptyPositionsList.size()));
            return ItemFactory.createRandomItem(itemPosition, random);
        }
        LOGGER.info("Runtime Exeption - all position was busy");
        throw new RuntimeException("all position was busy");
    }

    /**
     * Fight hero with all mobs which are placed on same cell with him.
     */
    private void intesectionHeroWithMob() {
        List<Mob> killedMob = new ArrayList<>();

        for (Mob currnetMob : mob) {
            if (hero.getPosition().equals(currnetMob.getPosition())) {
                LOGGER.info(String.format("%s with healf = %s  attack hero ",
                        currnetMob.getClass().getName(),
                        hero.getHealth()));
                hero.attack(currnetMob);
                LOGGER.info(String.format("after attact  %s with healf = %s",
                        currnetMob.getClass().getName(),
                        currnetMob.getHealth()));
                LOGGER.info(String.format("after attact hero healf = %s", hero.getHealth()));
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

    /**
     * Apply iof items which are placed on same cell with hero
     */
    private void applyItem() {
        for (Item item : inventory) {
            if (hero.getPosition().equals(item.getPosition())) {
                LOGGER.info(String.format("Hero get inventary %s", item.getName()));
                hero.addInvetory(item);
                hero.applyItem(hero.getInventory().size() - 1);
                inventory.remove(item);
                break;
            }
        }
    }
}
