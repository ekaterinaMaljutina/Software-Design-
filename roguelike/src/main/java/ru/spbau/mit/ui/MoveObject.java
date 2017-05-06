package ru.spbau.mit.ui;

import com.sun.istack.internal.NotNull;
import ru.spbau.mit.gameObject.ObjectCreator.hero.Hero;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Orc;
import ru.spbau.mit.gameObject.ObjectCreator.mob.Troll;

public interface MoveObject {

    void move(@NotNull final Hero hero);

    void move(@NotNull final Troll mob);

    void move(@NotNull final Orc orc);
}
