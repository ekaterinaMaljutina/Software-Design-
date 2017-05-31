package ru.spbau.mit.creatures;


import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.Util;
import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.gamePoint.Position;

import java.util.Collections;

import static org.mockito.Mockito.mock;

public class HeroTest {
    @Test
    public void testAttack() {
        Position position = mock(Position.class);
        Hero hero = new Hero(position);

        Attributes attr = hero.getAttributes();
        int health = attr.getHealth();
        Assert.assertEquals(100, health);
        int attack = attr.getAttack();
        Assert.assertEquals(10, attack);
        int defense = attr.getDefense();
        Assert.assertEquals(1, defense);

        Attributes attributesForAttack = new Attributes(attack, health, defense);
        Creature mobFirst = Util.createCreature(position, attributesForAttack);
        mobFirst.attack(hero);

        Assert.assertTrue(hero.isLived());
        Assert.assertEquals(health - mobFirst.getAttack() + defense, hero.getHealth());

        attributesForAttack = new Attributes(health, attack, defense);
        Creature mobSecond = Util.createCreature(position, attributesForAttack);

        mobSecond.attack(mobFirst);

        Assert.assertTrue(mobFirst.isLived());
        Assert.assertEquals(new Attributes(1, 100, 1), mobFirst.getAttributes());
        Assert.assertTrue(mobSecond.isLived());
        Assert.assertEquals(new Attributes(100, 10, 1), mobSecond.getAttributes());

        int defenseItem = 100;
        Item item = Util.createItem(mock(Position.class), new Attributes(0, 0, defense));
        hero.applyItem(item, true);
        hero.attack(mobFirst);

        Assert.assertEquals(new Attributes(1, 10, defense + defenseItem), hero.getAttributes());
        Assert.assertTrue(hero.isLived());
        Assert.assertFalse(mobFirst.isLived());
        Assert.assertEquals(new Attributes(0, 100, 1), mobFirst.getAttributes());
    }

    @Test
    public void ApplyItemFromListInventory() {
        Hero hero = new Hero(mock(Position.class));
        Attributes attributes = hero.getAttributes();
        Item item111 = Util.createItem(mock(Position.class), new Attributes(1, 1, 1));
        Item item222 = Util.createItem(mock(Position.class), new Attributes(2, 2, 2));
        Assert.assertEquals(Collections.emptyList(), hero.getInventory());

        hero.addInvetory(item111);
        hero.addInvetory(item222);
        Assert.assertEquals(2, hero.getInventory().size());

        int health = hero.getHealth();
        int attack = hero.getAttack();
        int defense = hero.getDefense();

        hero.applyItem(10);
        Assert.assertEquals(new Attributes(health, attack, defense), hero.getAttributes());
        hero.applyItem(0);
        Assert.assertEquals(new Attributes(health + 1, attack + 1, defense + 1),
                hero.getAttributes());

        hero.applyItem(1);
        Assert.assertEquals(new Attributes(health + 1 + 2, attack + 1 + 2, defense + 1 + 2),
                hero.getAttributes());
    }
}
