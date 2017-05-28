package ru.spbau.mit.ui;

import com.sun.istack.internal.NotNull;
import ru.spbau.mit.gameObject.GameObject.Item.Kit;
import ru.spbau.mit.gameObject.GameObject.Item.Shield;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Orc;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Troll;

/**
 * Implement visitor pattern
 */
public interface MoveObject {

    void move(@NotNull final Hero hero);

    void move(@NotNull final Troll mob);

    void move(@NotNull final Orc orc);

    void move(@NotNull final Kit kit);

    void move(@NotNull final Shield shield);
}
