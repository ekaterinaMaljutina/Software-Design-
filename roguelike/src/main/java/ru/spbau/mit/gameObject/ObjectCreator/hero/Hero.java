package ru.spbau.mit.gameObject.ObjectCreator.hero;

import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.moves.gamePoint.Position;
import ru.spbau.mit.ui.MoveObject;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Creature {

    /**
     * Hero attributes
     */
    private static final int HEALTH = 100;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 1;
    private static final Attributes attributes = new Attributes(HEALTH, ATTACK, DEFENSE);

    /**
     * List of the hero's items
     */
    private List<Item> inventory = new ArrayList<>();

    public Hero(Position position) {
        super(position, attributes);
    }

    @Override
    public void accept(MoveObject moveObject) {
        moveObject.move(this);
    }

    /***
     * Add item to inventory
     * @param item item for adding
     */
    public void addInvetory(Item item) {
        inventory.add(item);
    }

    /**
     * Applies item from inventory by index
     */
    public void applyItem(int idx) {
        if (idx > inventory.size()) {
            return;
        }

        Item item = inventory.get(idx);
        boolean active = !item.getActive();
        applyItem(item, active);
        if (!item.getActive()) {
            inventory.remove(item);
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

}

