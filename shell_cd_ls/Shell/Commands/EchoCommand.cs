﻿using System;
using System.Linq;

namespace Shell
{
    /// <summary>
    /// Command for printing string
    /// </summary>
    public class EchoCommand: Command
    {
        public EchoCommand(): base("echo", Command.EndlessArgsCount) { }

        /// <summary>
        /// Opens all setted files, concats it's to one line 
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
                base.output += arg.Content;
            }
            if (base.output == "")
            {
                CreateError("Некорректный тип аргументов");
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