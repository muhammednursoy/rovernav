import exception.RoverCommandNotValidException;
import rover.Rover;
import rover.RoverMap;
import rover.RoverPosition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoverApplication {
    public static final String DEFAULT_INPUT_FILE = "input.txt";

    private Scanner scanner;
    private List<Rover> rovers = new ArrayList<>();

    public static void main(String[] args) throws IOException, RoverCommandNotValidException {
        RoverApplication roverApplication = RoverApplication.initialize(args);
        roverApplication.run();

    }

    public static RoverApplication initialize(String[] args) throws FileNotFoundException {
        RoverApplication newRoverApplication = new RoverApplication();
        String filePath = DEFAULT_INPUT_FILE;
        if (args.length > 0)
            filePath = args[0];

        newRoverApplication.setScanner(new Scanner(new File(filePath)));
        return newRoverApplication;
    }

    public void run() throws RoverCommandNotValidException {
        RoverMap map = initializeRoverMap();

        while (scanner.hasNextLine()) {
            Rover rover = initializeRover(map);
            String cmdSequence = parseCmdSequence();

            rover.sendCommandSequence(cmdSequence);
            printRoverPosition(rover);

            rovers.add(rover);
        }

    }

    private RoverMap initializeRoverMap() {
        int maxX, maxY;
        maxX = scanner.nextInt();
        maxY = scanner.nextInt();
        scanner.nextLine();
        return new RoverMap(maxX, maxY);
    }

    private Rover initializeRover(RoverMap map) {
        Rover rover;
        String line = scanner.nextLine();
        while (line.isEmpty())
            line = scanner.nextLine();

        Scanner lineScanner = new Scanner(line);
        int x, y;
        String direction;
        x = lineScanner.nextInt();
        y = lineScanner.nextInt();
        direction = lineScanner.next();
        rover = new Rover(map, new RoverPosition(x, y, direction));

        return rover;
    }

    private String parseCmdSequence() {
        String cmdSequence = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                cmdSequence = line;
                break;
            }
        }
        return cmdSequence;
    }

    private void printRoverPosition(Rover rover) {
        System.out.println();
        System.out.println(rover.getX() + " " + rover.getY() + " " + rover.getDirection());
    }

    public List<Rover> getRovers() {
        return this.rovers;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
