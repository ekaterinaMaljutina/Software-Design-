import java.io.*;
import java.util.regex.Pattern;

public class Wc implements Command{

    /**
     * Wc command implementation
     */
    public Environment run(Environment state, String[] args, Printed printed) throws Exception{
        if (args.length == 0 && state.getEnvString() == ""){
            throw new Exception("no filename in args");
        }
        if (args.length == 0 && state.getEnvString() != "") {
            args = new String[]{Environment.fileName};
        }

        int lineCount = 0,
            wordsCount = 0;
        long bytes = 0;

        for (String fileName : args){
            String line = countValue(fileName);
            state.setToFile(line);

            if (printed.equals(Printed.YES)) {
                System.out.println(line);
            }
        }

        return state;
    }

    private String countValue(String fileName) throws Exception{
        int lineCount = 0,
                wordsCount = 0;
        long bytes = 0;

        if (fileName.length() == 0) {
            return null;
        }
        LineNumberReader file =  new LineNumberReader( new FileReader(fileName));
        String line  = file.readLine();
        while(line != null) {
            lineCount += 1;
            wordsCount += countNotEmptyWords(Parser.removeDuplicSpaces(line).split(" "));
            line = file.readLine();
        }

        file.close();

        bytes = getBytes(fileName);

        String sout = lineCount + " " + wordsCount + " " + bytes + "  ";

        if (fileName != Environment.fileName) {
            sout += fileName;
        }
        return sout;
    }

    private int countNotEmptyWords(String[] str){
        int count = 0;
        for (String words : str){
            if (words.length() != 0){
                count++;
            }
        }
        return count;
    }

    private long getBytes(String fileName ) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        long bytes = fileInputStream.getChannel().size();
        fileInputStream.close();
        return bytes;
    }

}
