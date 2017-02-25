
public interface Command {

    /**
     * Print required indicator
     */
    public enum Printed{ YES, NO }

    /**
     * Prototype for calling any command
     * @param state state of a process
     * @param args command args
     * @return
     */
    public Environment run(Environment state, String[] args, Printed printed) throws Exception;

}
