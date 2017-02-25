import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;


public class PreProcess {

    /**
     * table with environment variable
     */
    private HashMap<String, String> variable =  new HashMap<>();

    private static final String nameFile = "./src/main/resourse/EnvVariable.txt";
    private Scanner fileWithVariable;


    public PreProcess(){
        try {

            fileWithVariable = new Scanner(new File(nameFile));
            while (fileWithVariable.hasNext()){
                variable.put(fileWithVariable.next(), fileWithVariable.next());
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("file is not found" + nameFile);
            System.exit(0);
        }
    }

    /**
     * Set value for enviroment variable
     * @param name name of new environment variable
     * @param value value of new environment variable
     */
    public void setVariable(String name, String value){
        try {
            FileWriter file = new FileWriter(nameFile, true);
            file.write(name + " " + value  + "\n");
            variable.put(name, value);
            file.close();
        }
        catch (IOException ex){
            System.out.println("file is not found " + nameFile);
        }
    }

    /**
     * get environment variable
     * @param name name environment variable
     */
    public String getVariable(String name){
        return variable.get(name);
    }


    /**
     * Check variable exsistence by it's name
     */
    public boolean hasVariable(String name){
        return variable.containsKey(name);
    }

    /**
     * replaces all environment variables with their value
     * @param str name of environment variable
     */
    public String preprocess(String str){
        String res = str;
        int idx = str.indexOf("$");

        while(idx >= 0){
           int idxNext = str.substring(idx).indexOf("/") + idx;
            if (idxNext - idx < 0) {
                idxNext = str.substring(idx).indexOf(" ") + idx;
                if (idxNext - idx < 0 ) {
                    idxNext = str.substring(idx).indexOf("\"") + idx;
                    if (idxNext - idx < 0) {
                        idxNext = str.length();
                    }
                }
            }
            str = str.substring(0, idx) + variable.get(str.substring(idx+1, idxNext)) + str.substring(idxNext);
            idx = str.indexOf("$");
            res = str;
        }

        return str;
    }
}
