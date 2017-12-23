package rover;

import exception.RoverCommandNotValidException;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    private static final String[] VALID_COMMANDS = {"L", "R", "M"};

    private RoverMap map;
    private RoverPosition position;

    public Rover(RoverMap map, RoverPosition position){
        this.map = map;
        this.position = position;
    }

    public void sendCommandSequence(String cmdSeq) throws RoverCommandNotValidException {
        List<String> cmdList = getCmdList(cmdSeq);
        for (String cmd: cmdList)
            executeCommand(cmd);
    }

    private List<String> getCmdList(String cmdSeq) throws RoverCommandNotValidException {
        List<String> cmdList = new ArrayList<>();
         for (char chrCmd: cmdSeq.toCharArray()){
             String strCmd = String.valueOf(chrCmd);
             if (isCmdValid(strCmd)){
                 cmdList.add(strCmd);
             }else {
                 throw new RoverCommandNotValidException(strCmd);
             }
         }
         return cmdList;
    }

    public void executeCommand(String cmd) throws RoverCommandNotValidException {
        switch (cmd){
            case "L":
                turnLeft();
                break;
            case "R":
                turnRight();
                break;
            case "M":
                moveForward();
                break;
            default:
                throw new RoverCommandNotValidException(cmd);
        }
    }

    private boolean isCmdValid(String str_cmd) {
        boolean valid = false;
        for (String valid_cmd: VALID_COMMANDS){
            if (str_cmd.equals(valid_cmd))
                valid = true;
        }
        return valid;
    }

    private void turnLeft() {
        switch (position.getDirection()){
            case "N":
                position.setDirection("W");
                break;
            case "S":
                position.setDirection("E");
                break;
            case "E":
                position.setDirection("N");
                break;
            case "W":
                position.setDirection("S");
                break;
        }
    }

    private void turnRight() {
        switch (position.getDirection()){
            case "N":
                position.setDirection("E");
                break;
            case "S":
                position.setDirection("W");
                break;
            case "E":
                position.setDirection("S");
                break;
            case "W":
                position.setDirection("N");
                break;
        }
    }

    private void moveForward() {
        switch (position.getDirection()){
            case "N":
                checkAndIncreaseY();
                break;
            case "S":
                checkAndDecreaseY();
                break;
            case "E":
                checkAndIncreaseX();
                break;
            case "W":
                checkAndDecreaseX();
                break;
        }
    }

    private void checkAndIncreaseY() {
        if (position.getY() < map.getMaxY())
            position.increaseY();
    }

    private void checkAndDecreaseY() {
        if (position.getY() > 0)
            position.decreaseY();
    }

    private void checkAndIncreaseX() {
        if (position.getX() < map.getMaxX())
            position.increaseX();
    }

    private void checkAndDecreaseX() {
        if (position.getX() > 0)
            position.decreaseX();
    }

    public String getDirection() {
        return position.getDirection();
    }

    public int getY() {
        return position.getY();
    }

    public int getX() {
        return position.getX();
    }

    public static String[] getValidCommands() {
        return VALID_COMMANDS;
    }
}
