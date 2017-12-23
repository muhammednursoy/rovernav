package exception;

public class RoverCommandNotValidException extends Exception {
    private static final String msg = "This is not a valid command: ";
    private String invalidCmd;

    public RoverCommandNotValidException(String cmd) {
        super(msg + cmd);
        this.invalidCmd = cmd;
    }

    public String getInvalidCmd() {
        return invalidCmd;
    }
}
