import java.io.File;
import java.lang.Exception;

public class Pwd implements Command {

    /**
     * Pwd command implementation
     */
    public Environment run(Environment state, String[] args, Printed printed) throws Exception{

        String currentDir = System.getProperty("user.dir");
        if (printed.equals( Printed.YES)) {
            System.out.println(currentDir);
        }
        state.setToFile(currentDir);

        return state;

    }
}
