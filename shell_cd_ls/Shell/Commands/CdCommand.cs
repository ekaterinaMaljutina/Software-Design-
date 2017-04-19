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
        private String roodDirectory = null;

        public CdCommand(): base("cd", Command.EndlessArgsCount) {
            currentDirectory = Command.currentDirectory;
            roodDirectory = Directory.GetDirectoryRoot(currentDirectory);
         }


         private void PrintCurrentDir() 
         {
             base.output += String.Format("current directory is: {0}", currentDirectory);
         }

         private Tuple<bool,String> checkDirectory(String toDirectory) 
         {
            bool checkDirectory = false;
            String[] filesCurrentDirectory = Directory.GetDirectories(currentDirectory);
            
            String fullPathToDir = currentDirectory + "/" + toDirectory;
            
            foreach (String file in filesCurrentDirectory) 
            {
                if (file.Equals(fullPathToDir)) 
                {
                    checkDirectory = true;
                }
            }
            return new Tuple<bool,String>(checkDirectory, fullPathToDir);
         }

        /// <summary>
        /// Opens all setted files, concats it's to one line 
        /// </summary>
        public override void CreateOutput()
        {
            base.output = "";
            if (base.args.Count() == Command.NoArgsCount)
            {
                currentDirectory = roodDirectory;
                PrintCurrentDir();
                return;
            }

            if (base.args.Last() != null && base.args.Last().Type == TypeCode.String) 
            {
                String toDirectory = base.args.Last().Content;

                switch (toDirectory) 
                {
                    case ".." : //back directory                        
                        if (!currentDirectory.Equals("/")) 
                        {
                            currentDirectory = Directory.GetParent(currentDirectory).FullName;
                        }
                        break;

                    case "." : // empty 
                        break;
                    
                    default:
                        Tuple<bool, String> res = checkDirectory(toDirectory); 
                        if (res.Item1) 
                        {
                            currentDirectory = res.Item2;
                        } else {
                            Console.WriteLine("no such file or directory");
                        }
                        break;
                }   
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
        }
    }
}