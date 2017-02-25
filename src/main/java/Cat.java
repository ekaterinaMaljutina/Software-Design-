import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cat implements Command {

    /**
     * cat command implementation
     */
    public Environment run(Environment state, String[] args, Printed printed) throws Exception{
        if (args.length == 0 && state.getEnvString() == "") {
            Scanner sc = new Scanner(System.in).useDelimiter("\n");
            while (true) {
                try {
                    String line = sc.next();
                    state.setToFile(line);
                    System.out.println(line);
                } catch (NoSuchElementException ex) {
                    break;
                }
            }
        }
        if (args.length == 0)
            args = new String[]{Environment.fileName};

        for (String fileName : args){
            try {
                String str = readFile(fileName);
                state.setToFile(str);
                if (printed .equals(Printed.YES)) {
                    System.out.println(str);
                }
            }
            catch (IOException ex){
                System.out.println("not found file " + fileName);
                return null;
            }
        }
        return  state;
    }

    private String readFile(String fileName) throws IOException{
        File file = new File(fileName);
        InputStream inputStream = new FileInputStream(file);
        int size = inputStream.available();
        String str = "";
        for(int i = 0; i < size; i++) {
            str += (char)inputStream.read();
        }
        inputStream.close();
        return str;
    }

}
