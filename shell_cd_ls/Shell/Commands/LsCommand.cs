using System;
using System.Linq;
using System.IO;

namespace Shell
{
    /// <summary>
    /// Command for printing string
    /// </summary>
    
    public class LsCommand: Command
    {
        public LsCommand(): base("ls", Command.EndlessArgsCount) { }   

        private void PrintFilesInDir (String dir, String baseDir)
        {
            try
            {
                String[] res = Directory.GetFileSystemEntries(dir);
               
                foreach (String str in res)
                {
                    base.output += String.Format("{0}  ", str.Replace(dir, ""));
                }
            }
            catch(DirectoryNotFoundException)
            {
                String fullPath = currentDirectory;
                if (currentDirectory[currentDirectory.Length - 1] != '\\')
                {
                    fullPath += "\\";
                }
                fullPath += dir;
                PrintFilesInDir(fullPath, baseDir);
            }
            catch (Exception)
            {
                Console.WriteLine("current dir = " + baseDir + ", not found");
            }
        }
        
        /// <summary>
        /// Opens all setted files, concats it's to one line 
        /// </summary>
        public override void CreateOutput()
        {
            base.output = "";
            if (base.args.Count() == Command.NoArgsCount)
            {
                PrintFilesInDir (Command.currentDirectory, Command.currentDirectory);
                base.CreateOutput();
                return;
            }
            if (base.args.Last() != null && base.args.Last().Type == TypeCode.String) 
            {
                String getDir = base.args.Last().Content;
                PrintFilesInDir(getDir, getDir);
            }
            else 
            {
                Console.WriteLine("more one args in command ls");
            }
            base.CreateOutput();

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