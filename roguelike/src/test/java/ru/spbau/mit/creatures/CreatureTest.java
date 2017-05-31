package ru.spbau.mit.creatures;


import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.Util;
import ru.spbau.mit.gameObject.GameObject.Attributes;
import ru.spbau.mit.gameObject.GameObject.Item.Item;
import ru.spbau.mit.gameObject.GameObject.Item.ItemFactory;
import ru.spbau.mit.gameObject.ObjectCreator.Creature;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Mob;
import ru.spbau.mit.gameObject.ObjectCreator.mob.MobFactory;
import ru.spbau.mit.gameObject.ObjectCreator.mob.MobType;
import ru.spbau.mit.moves.gamePoint.Position;

import static org.mockito.Mockito.mock;

public class CreatureTest {
    @Test
    public void ApplyItemsTest() {
        Attributes attributes = new Attributes(10, 1, 1);
        Creature creature = Util.createCreature(mock(Position.class), attributes);

        Position itemPosition = mock(Position.class);
        Attributes itemAttributes = new Attributes(5, 0, 1);
        Item item = Util.createItem(itemPosition, itemAttributes);

        /* apply active item */
        creature.applyItem(item, true);
        Assert.assertEquals(new Attributes(15, 1, 2), creature.getAttributes());

        /* appply not active item */
        creature.applyItem(item, false);
        Assert.assertEquals(new Attributes(10, 1, 1), creature.getAttributes());
    }

    @Test
    public void ItemFactoryTest() {
        Item item = ItemFactory.createItem(mock(Position.class), ItemFactory.TYPE.KIT);
        Assert.assertEquals(ItemFactory.TYPE.KIT.name(), item.getName().toUpperCase());
        Assert.assertEquals(new Attributes(5, 0, 0), item.getAttributes());

        item = ItemFactory.createItem(mock(Position.class), ItemFactory.TYPE.SHIELD);
        Assert.assertEquals(ItemFactory.TYPE.SHIELD.name(), item.getName().toUpperCase());
        Assert.assertEquals(new Attributes(0, 0, 1), item.getAttributes());
    }

    @Test
    public void MobFactoryTest() {
        Mob mob = MobFactory.create(mock(Position.class), MobType.ORC);
        Assert.assertEquals(MobType.ORC.name(), mob.getClass().getSimpleName().toUpperCase());
        Assert.assertEquals(new Attributes(30, 10, 0), mob.getAttributes());

        mob = MobFactory.create(mock(Position.class), MobType.TROLL);
        Assert.assertEquals(MobType.TROLL.name(), mob.getClass().getSimpleName().toUpperCase());
        Assert.assertEquals(new Attributes(10, 5, 0), mob.getAttributes());
    }
}
