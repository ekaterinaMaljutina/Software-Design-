﻿using System;
using System.Linq;
using System.IO;

namespace Shell
{
    /// <summary>
    /// Command for printing file
    /// </summary>
    public class CatCommand: Command
    {
        public CatCommand(): base("cat", Command.EndlessArgsCount) { }

        /// <summary>
        /// Opens all setted files, concat it's to one line 
        /// </summary>
        public override void CreateOutput()
        {
            base.output = "";

            if (base.args.Count() == Command.NoArgsCount)
            {
                CreateError("Аргументы не заданы");
                return;
            }

            foreach (Argument arg in base.args)
            {
                if (arg == null || arg.Type != TypeCode.String)
                {
                    continue;
                }
                Console.WriteLine("context = " + Command.currentDirectory + arg.Content);
                if (!(new FileInfo(Command.currentDirectory + "\\" + arg.Content).Exists))
                {
                    continue;
                }
                StreamReader file = new StreamReader(Command.currentDirectory + "\\"  + arg.Content);
                base.output += file.ReadToEnd();
                file.Close();
            }

            if (base.output == "")
            {
                CreateError("Файл не найден");
            }
            else {
                base.CreateOutput();
            }
        }

        /// <summary>
        /// Executes command of printing files (prints files to console)
        /// </summary>
        public override void Execute()
        {
            CreateOutput();
            Console.WriteLine(base.output);
        }
    }
}