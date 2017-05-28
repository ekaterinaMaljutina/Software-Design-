package ru.spbau.mit.creatures;


import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.Util;
import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.moves.gamePoint.Position;

import static org.mockito.Mockito.mock;

public class HeroTest {
    @Test
    public void testAttack() {
        Position position = mock(Position.class);
        Hero hero = new Hero(position);

        Attributes attributes = hero.getAttributes();
        int healf = attributes.getHealth();
        int attack = attributes.getAttack();
        int defense = attributes.getDefense();

        Attributes attributesForAttack = new Attributes(attack, healf, defense);
        Creature creatureObj = Util.createCreature(position, attributesForAttack);
        creatureObj.attack(hero);

        Assert.assertFalse(hero.isLived());

        attributesForAttack = new Attributes(healf, attack, defense);
        Creature creatureObj1 = Util.createCreature(position, attributesForAttack);

        creatureObj1.attack(creatureObj);

        Assert.assertFalse(creatureObj.isLived());
        Assert.assertTrue(creatureObj1.isLived());
    }


}
