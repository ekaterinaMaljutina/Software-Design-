import java.util.Arrays;

public class RunCommand {

    private PreProcess preProcessCommand = new PreProcess();

    /**
     * choose command for running
     * @param nameCommand name of command
     * @param args command arguments
     * @param environment process state
     * @param printed print results or not
     */
    private Environment chooseCommand(String nameCommand, String[] args,
                                      Environment environment, Command.Printed printed) throws Exception{
        switch (nameCommand){

            case "pwd":
                Pwd pwd = new Pwd();
                environment = pwd.run(environment, args, printed);
                break;

            case "echo":
                Echo echoCommand = new Echo();
                environment = echoCommand.run(environment, args, printed);
                break;

            case "cat":
                Cat catCommand = new Cat();
                environment = catCommand.run(environment, args, printed);
                break;

            case "wc":
                Wc wcCommand = new Wc();
                environment = wcCommand.run(environment, args, printed);
                break;

            case "exit":
                System.exit(1);
                break;

            case "":
                System.out.println("");
                break;

            default:
                throw new Exception("command name not found");
        }
        return environment;
    }

    /**
     * Get line from IO, run parsing, check that command arguments are quoted.
     * If full quoted term found, then replace all occurence of it by environment variable.
     * Commands like NAME = VALUE finished by pushing (NAME, VALUE) into table with environment variables
     * @param str : line from IO
     * @return result of commands
     */
    public String run(String str) throws Exception{
        String[] commands = Parser.parseUsePipe(str);

        Environment environment = new Environment();

        Command.Printed printed = Command.Printed.NO;

        int idx = 0;
        for (String command : commands){
            if (command.indexOf("=") >= 0){
                String[] envVariable = Parser.parseEnvVariable(command);

                assert (envVariable.length == 2);

                String nameVariable = Parser.removeSpacesString(envVariable[0]),
                        valueVariable = Parser.removeSpacesString(envVariable[1]);
                System.out.println(nameVariable + " " + valueVariable);

                preProcessCommand.setVariable(nameVariable, valueVariable);
                return "";

            }
            if (!Parser.checkWeakQuoting(command)) {
                command = preProcessCommand.preprocess(command);
            }

            command = Parser.removeSpacesString(command);
            String[] parseCommand = Parser.parseCommand(command);
            parseCommand = Parser.removeSpacesFromArray(parseCommand);

            if (parseCommand.length == 0) {
                throw  new Exception("command not name");
            }
            if (idx == commands.length -1 ) {
                printed = Command.Printed.YES;
            }

            environment = chooseCommand(parseCommand[0], Arrays.copyOfRange(parseCommand, 1, parseCommand.length),
                    environment, printed);
            idx++;
        }
        String res = environment.getEnvString();
        environment.removeEnv();
        return res;
    }
}
