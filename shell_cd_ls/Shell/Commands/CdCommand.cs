using System;
using System.Linq;
using System.IO;

namespace Shell
{
    /// <summary>
    /// Command for printing string
    /// </summary>

    public class CdCommand: Command
    {
        private string rootDirectory;

        public CdCommand(): base("cd", Command.EndlessArgsCount) {
            currentDirectory = Command.currentDirectory;
            rootDirectory = Directory.GetDirectoryRoot(currentDirectory);
         }


         private void PrintCurrentDir() 
         {
            base.output = currentDirectory;
         }

         private Tuple<bool,String> CheckDirectory(String toDirectory) 
         {
            bool checkDirectory = false;
            
            String[] filesCurrentDirectory = Directory.GetDirectories(currentDirectory);
         
            String fullPathToDir = currentDirectory;
            if (currentDirectory[currentDirectory.Length - 1] != '\\')
            {
                fullPathToDir += "\\";
            }
            fullPathToDir += toDirectory;
            foreach (String file in filesCurrentDirectory) 
            {
                if (file.Equals(fullPathToDir)) 
                {
                    checkDirectory = true;
                }
            }
            return new Tuple<bool,String>(checkDirectory, fullPathToDir);
         }

        private void DefaultNextPath(string path)
        {
            string[] paths = path.Split('\\');

            if (paths.Length > 1)
            {
                foreach (string str in paths)
                {
                    if (str != null && str != "")
                    {
                        NextPath(str);
                    }
                }

            }
            else
            {
                Tuple<bool, String> res = CheckDirectory(path);
                if (res.Item1)
                {
                    currentDirectory = res.Item2;
                }
                else
                {
                    Console.WriteLine("no such file or directory");
                }
            }
        }

        private void NextPath(string path)
        {
            switch (path)
            {
                case "..": //back directory                        
                    if (!currentDirectory.Equals("/") && !currentDirectory.Equals(rootDirectory))
                    {
                        currentDirectory = Directory.GetParent(currentDirectory).FullName;
                    }
                    break;
                case ".": // empty 
                    break;
                case "--":
                    currentDirectory = rootDirectory;
                    break;
                default:
                    DefaultNextPath(path);
                    break;
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
                currentDirectory = rootDirectory;
                PrintCurrentDir();
                return;
            }

            if (base.args.Last() != null && base.args.Last().Type == TypeCode.String) 
            {
                String toDirectory = base.args.Last().Content;
                NextPath(toDirectory);
                PrintCurrentDir();        
            }
            else 
            {
                Console.WriteLine("more one args in command cd");
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
            Command.currentDirectory = currentDirectory;
        }
    }
}