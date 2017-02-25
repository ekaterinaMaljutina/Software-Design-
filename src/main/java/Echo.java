
public class Echo implements Command {

    /**
     * Echo command implementation
     */
    public Environment run(Environment state, String[] args, Printed printed) throws Exception{
        String lines = "";
        for (String str : args){
            lines += str + " ";
        }
        if (printed.equals(Printed.YES)) {
            System.out.println(lines);
        }
        state.setToFile(lines);
        return state;

    }

}
