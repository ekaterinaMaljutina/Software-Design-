import java.util.NoSuchElementException;
import java.util.Scanner;

public class Shell {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        RunCommand runCommaner = new RunCommand();
        while(true){
            try {
                String line = sc.next();
                runCommaner.run(line);
            }catch (NoSuchElementException ex){
                break;
            }
        }
    }
}
