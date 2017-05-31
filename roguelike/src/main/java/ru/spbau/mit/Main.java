package ru.spbau.mit;

import ru.spbau.mit.game.Game;
import ru.spbau.mit.moves.GameFrame;

/**
 * Main class, which run game
 */
public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            GameFrame frame = new GameFrame(game);
            frame.setVisible(true);
            game.run();
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
