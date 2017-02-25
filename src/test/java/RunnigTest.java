import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class RunnigTest {
    private RunCommand runCommaner = new RunCommand();

    @Test
    public void runPwd() throws Exception{
        String line = "   pwd  ";
        runCommaner.run(line);
    }


    @Test
    public void  runCat() {
        String fileName = "./src/main/resourse/EnvVariable.txt";
        try {

            String line = " cat  " + fileName;
            String res = runCommaner.run(line);

            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            String str = "";
            for(int i = 0; i < size; i++) {
                str += (char)inputStream.read();
            }
            inputStream.close();

            Assert.assertEquals(str, res);
        }
        catch (Exception ex){
            System.out.println("file " + fileName +" not fount in dir " + System.getProperty("user.dir") );
            System.exit(0);
        }
    }

    @Test
    public void runEcho() throws Exception{
        String line  = "echo     pam_pam    djjdj    askkasd";
        String res = runCommaner.run(line);

        Assert.assertEquals(Parser.removeDuplicSpaces(line.substring(5)), Parser.removeSpacesString(res));
    }

    @Test
    public void runWc() throws Exception{
        String fileName = "./build.gradle";
        String line = "wc " + fileName;


        String res = runCommaner.run(line);
        Assert.assertEquals("13 21 209  " + fileName, res);
    }

}


