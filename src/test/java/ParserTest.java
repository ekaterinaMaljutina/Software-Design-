import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public void parserStringToCommands(){
        String str = "pwd | echo";
        String[] command = Parser.parseUsePipe(str);

        command = Parser.removeSpacesFromArray(command);

        Assert.assertEquals(2, command.length);
        Assert.assertEquals("pwd", command[0]);
        Assert.assertEquals("echo", command[1]);
    }

    @Test
    public void parseCommandToArgs(){
        String str =  " cat   ./build.gradle   ./settings.gradle  ";
        String[] command = Parser.parseUsePipe(str);

        Assert.assertEquals(1, command.length);

        String[] args = Parser.parseCommand(command[0]);
        for (String s: args){
            System.out.println(s);
        }
        Assert.assertEquals(3, args.length);
        Assert.assertEquals("cat", args[0]);
        Assert.assertEquals("./build.gradle", args[1]);
        Assert.assertEquals("./settings.gradle", args[2]);
    }
}
