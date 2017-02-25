import java.io.*;
import java.nio.file.Path;

public class Environment {

    public static final String fileName = "enviroment.txt";

    /**
     * Gets process state
     * @return
     */
    public String getEnvString(){
        try {
            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            String str = "";
            for(int i = 0; i < size; i++) {
                str += (char)inputStream.read();
            }
            return str;
        }
        catch (IOException ex){
            System.out.println("not found file " + fileName);
            return  "";
        }
    }

    /**
     * Sets process state
     * @param str
     */
    public void setToFile(String str) throws IOException{
        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(str.getBytes());
    }


    /**
     * Removes process state
     */
    public void removeEnv() throws IOException{
        FileOutputStream writer = new FileOutputStream(fileName);
        writer.write(("").getBytes());
        writer.close();
    }

}
