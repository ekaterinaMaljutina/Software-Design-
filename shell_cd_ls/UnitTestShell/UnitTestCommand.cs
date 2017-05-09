using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shell;
using System.IO;

namespace UnitTestShell
{
    [TestClass]
    public class UnitTestCommand
    {
        [TestMethod]
        public void TestMethodPwd()
        {
            PwdCommand pwd = new PwdCommand();
            pwd.Execute();
            Assert.AreEqual(Directory.GetCurrentDirectory(), ArgumentStorer.Find("pwd").Content);
        }

        [TestMethod]
        public void TestMethodCat()
        {
            CatCommand cat = new CatCommand();
            string file1 = @"../../../example.txt";
            string file2 = @"../../../synmaster.txt";
            cat.AddArgument(new Argument(file1, TypeCode.String));
            cat.AddArgument(new Argument(file2, TypeCode.String));
            cat.Execute();
            String result = new StreamReader(file1).ReadToEnd() + new StreamReader(file2).ReadToEnd();
            Assert.AreEqual(result, ArgumentStorer.Find("cat").Content);

            cat.AddArgument(new Argument("1.txt", TypeCode.String));
            cat.Execute();
            Assert.IsTrue(ArgumentStorer.Find("cat").Content.StartsWith("Error "));
        }

        [TestMethod]
        public void TestMethodWc()
        {
            WcCommand wc = new WcCommand();
            string file = @"../../../example.txt";
            wc.AddArgument(new Argument(file, TypeCode.String));
            wc.Execute();
            String text = new StreamReader(file).ReadToEnd();
            String result = text.Split('\n').Length.ToString() + " " + text.Split(' ', '\n').Length.ToString() + " " + new FileInfo(file).Length;
            Assert.AreEqual(result, ArgumentStorer.Find("wc").Content);

            wc.AddArgument(new Argument("123", TypeCode.String));
            wc.Execute();
            Assert.AreEqual("1 1 3", ArgumentStorer.Find("wc").Content);
        }

        [TestMethod]
        public void TestMethodEcho()
        {
            EchoCommand echo = new EchoCommand();
            echo.AddArgument(new Argument("Hello, World", TypeCode.String));
            echo.AddArgument(new Argument("!!!", TypeCode.String));
            echo.Execute();
            Assert.AreEqual("Hello, World!!!", ArgumentStorer.Find("echo").Content);

            echo.Execute();
            Assert.IsTrue(ArgumentStorer.Find("echo").Content.StartsWith("Error "));
        }

        [TestMethod]
        public void TestMethodCd()
        {
            CdCommand cd = new CdCommand();
            cd.AddArgument(new Argument(".", TypeCode.String));
            cd.Execute();

            String path = Directory.GetCurrentDirectory();
            Assert.AreEqual(path, ArgumentStorer.Find("cd").Content);

            cd.AddArgument(new Argument("..", TypeCode.String));
            cd.Execute();

            path = Directory.GetParent(path).FullName;
            Assert.AreEqual(path, ArgumentStorer.Find("cd").Content);

            cd.AddArgument(new Argument("..", TypeCode.String));
            cd.Execute();

            path = Directory.GetParent(path).FullName;
            Assert.AreEqual(path, ArgumentStorer.Find("cd").Content);

            cd.Execute();
            path = Directory.GetDirectoryRoot(path);
            Assert.AreEqual(path, Command.currentDirectory);

        }


        [TestMethod]
        public void TestMethodCdInDepth()
        {
            CdCommand cd = new CdCommand();
            cd.AddArgument(new Argument("..\\..", TypeCode.String));
            cd.Execute();

            String path = Directory.GetParent(Directory.GetParent( Directory.GetCurrentDirectory()).FullName).FullName;
            Assert.AreEqual(path, ArgumentStorer.Find("cd").Content);

            cd.AddArgument(new Argument("..", TypeCode.String));
            cd.Execute();

            path = Directory.GetParent(path).FullName;
            Assert.AreEqual(path, ArgumentStorer.Find("cd").Content);

        }

        private String prepare(String path) {
            String res = "";
            foreach (string str in Directory.GetFileSystemEntries(path) ) 
            {
                res += String.Format("{0}  ",str.Replace(path,""));
            }
            return res;
        }

        [TestMethod]
        public void TestMethodLs() 
        {
            LsCommand ls = new LsCommand();
            ls.Execute();
            String path = Command.currentDirectory;
            String res = prepare(path);
            Assert.AreEqual(res, ArgumentStorer.Find("ls").Content);

            CdCommand cd = new CdCommand();
            cd.AddArgument(new Argument("..",TypeCode.String));
            cd.Execute();

            path = Command.currentDirectory;

            res = prepare(path);
            ls.Execute();
            Assert.AreEqual(res, ArgumentStorer.Find("ls").Content);
        }

        [TestMethod]
        public void TestMethodLsNotExistDir()
        { 
            LsCommand ls = new LsCommand();
            ls.AddArgument(new Argument("arejwjkrwejr", TypeCode.String));
            ls.Execute();
            Assert.AreEqual("", ArgumentStorer.Find("ls").Content);
        }
    }
}
