package ru.spbau.mit.commandCreator;

import ru.spbau.mit.command.*;
import ru.spbau.mit.command.echo.Echo;
import ru.spbau.mit.command.exit.Exit;
import ru.spbau.mit.command.cat.Cat;
import ru.spbau.mit.command.grep.Grep;
import ru.spbau.mit.command.pwd.Pwd;
import ru.spbau.mit.command.wc.Wc;

/**
 * Choose command by command name
 */
public class CommandCreate {

    /**
     * Get command by name
     *
     * @param nameCommand name command
     * @return created command
     * @throws Exception command name not found
     */
    public static Command getCommand(String nameCommand) throws Exception {
        Command command;

        switch (nameCommand) {
            case "pwd":
                command = new Pwd();
                break;

            case "echo":
                command = new Echo();
                break;

            case "cat":
                command = new Cat();
                break;

            case "wc":
                command = new Wc();
                break;

            case "exit":
                command = new Exit();
                break;

            case "grep":
                command = new Grep();
                break;

            default:
                NotFoundCommandNameException ex = new NotFoundCommandNameException();
                return null;
        }
        return command;
    }
}
