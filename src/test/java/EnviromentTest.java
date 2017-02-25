import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class EnviromentTest {

    @Test
    public void checkEnv() throws IOException{
        Environment environment = new Environment();

        String str = "asdasd  \n " +
                "sadkadskl  \n" +
                "smdkff\n";

        environment.setToFile(str);

        Assert.assertEquals(str, environment.getEnvString());

        environment.removeEnv();

        str = "1234";

        environment.setToFile(str);

        Assert.assertEquals(str, environment.getEnvString());
    }
}
