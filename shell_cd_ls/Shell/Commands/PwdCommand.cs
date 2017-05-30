﻿using System;
using System.IO;

namespace Shell
{
    /// <summary>
    /// Command for printing current directory
    /// </summary>
    public class PwdCommand: Command
    {
        public PwdCommand(): base("pwd", Command.NoArgsCount) { }

        /// <summary>
        /// Gets name of current directory
        /// </summary>
        public override void CreateOutput()
        {
            base.output = Command.currentDirectory;
            base.CreateOutput();
        }

        /// <summary>
        /// Prints name of current directory to console
        /// </summary>
        public override void Execute()
        {
            CreateOutput();
            Console.WriteLine(base.output);
        }
    }
}