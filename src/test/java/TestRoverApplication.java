import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import exception.RoverCommandNotValidException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestRoverApplication {

    private static final String TEST_INPUT = "5 5\n" +
            "\n" +
            "1 2 N\n" +
            "\n" +
            "LMLMLMLMM\n" +
            "\n" +
            "3 3 E\n" +
            "\n" +
            "MMRMMRMRRM";

    @Test
    public void test_RoverApplication() throws FileNotFoundException, RoverCommandNotValidException {
        RoverApplication roverApplication = new RoverApplication();
        roverApplication.setScanner(new Scanner(TEST_INPUT));
        roverApplication.run();
        assertEquals(2, roverApplication.getRovers().size());
    }

    @Test
    public void test_RoverApplicationMain() throws IOException, RoverCommandNotValidException {
        String[] args = {"neverExistedTestFile"};
        try{
            RoverApplication.main(args);
            fail();
        }catch (FileNotFoundException e){
            assertTrue(e.getMessage().contains(args[0]));
        }
    }
}
