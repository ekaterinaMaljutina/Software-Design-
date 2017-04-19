using System;
using System.Linq;

namespace Shell
{
    /// <summary>
    /// Represents main command line's entity
    /// </summary>
    public static class Shell
    {
        static public void InitDictionary()
        {
            CommandStorer.Add("pwd", new PwdCommand());
            CommandStorer.Add("echo", new EchoCommand());
            CommandStorer.Add("cat", new CatCommand());
            CommandStorer.Add("wc", new WcCommand());
            CommandStorer.Add("exit", new ExitCommand());
            CommandStorer.Add("cd", new CdCommand());
            CommandStorer.Add("ls", new LsCommand());
        }

        static public void Start()
        {
            InitDictionary();
            while (true)
            {
                String input = Console.ReadLine();
                if (input == "")
                {
                    continue;
                }

                var comResult = (new Expression(input)).Interpret();
                if (comResult != null) {
                    Command currentCommand = comResult.First() as Command;
                    currentCommand.Execute();
                    Console.WriteLine("currentCommand name {0} ",currentCommand.Name);
                    Console.WriteLine("cd currentDirectory {0}",Command.currentDirectory);
                    CommandStorer.ChangeItemValue("ls", Command.currentDirectory);
                    Console.WriteLine("ls currentDirectory {0}",Command.currentDirectory);

                }
            }
        }
    }
}
