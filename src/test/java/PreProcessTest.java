import org.junit.Assert;
import org.junit.Test;

public class PreProcessTest {

    @Test
    public void setVariableTest(){
        PreProcess preProcess = new PreProcess();
        preProcess.setVariable("test", "testValue");

        Assert.assertTrue(preProcess.hasVariable("test"));

    }

    @Test
    public void preprocesTestOne(){
        PreProcess preProcess = new PreProcess();
        String value = "/home";
        preProcess.setVariable("HOME", value);

        String str = "cat $HOME/value.txt";
        String result = preProcess.preprocess(str);

        Assert.assertEquals("cat " + value + "/value.txt", result);
    }


    @Test
    public void preprocessTestTwo(){
        PreProcess preProcess = new PreProcess();
        String value = "/home";
        preProcess.setVariable("HOME", value);

        String value2 = "name";
        preProcess.setVariable("NAME", value2);

        Assert.assertTrue(preProcess.hasVariable("NAME"));


        String str = "cat $HOME/$NAME/value.txt";
        String result = preProcess.preprocess(str);

        Assert.assertEquals("cat " + value + "/" + value2 + "/value.txt", result);

    }

    @Test
    public void preprocessTestFree(){
        PreProcess preProcess = new PreProcess();
        String value = "/home";
        preProcess.setVariable("HOME", value);

        String value2 = "name";
        preProcess.setVariable("NAME", value2);

        Assert.assertTrue(preProcess.hasVariable("NAME"));


        String str = "cat $HOME/$NAME";
        String result = preProcess.preprocess(str);

        Assert.assertEquals("cat " + value + "/" + value2, result);

    }
}
