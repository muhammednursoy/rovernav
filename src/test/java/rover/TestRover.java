package rover;

import exception.RoverCommandNotValidException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestRover {
    private Rover rover;

    public void setUpRover(int x, int y, String direction) {
        RoverMap map = new RoverMap(5, 5);
        RoverPosition position = new RoverPosition(x, y, direction);
        rover = new Rover(map, position);
    }

    @Test
    public void test_RoverSetUp() throws Exception {
        RoverMap map = new RoverMap(5,5);
        RoverPosition position = new RoverPosition(2,2,"W");
        Rover rover = new Rover(map, position);
        assertEquals("W", rover.getDirection());
        assertEquals(2, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverLeftNorthToWest() throws Exception {
        setUpRover(1, 2, "N");
        rover.executeCommand("L");
        assertEquals("W", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverLeftWestToSouth() throws Exception {
        setUpRover(1, 2, "W");
        rover.executeCommand("L");
        assertEquals("S", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverLeftSouthToEast() throws Exception {
        setUpRover(1, 2, "S");
        rover.executeCommand("L");
        assertEquals("E", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverLeftEastToNorth() throws Exception {
        setUpRover(1, 2, "E");
        rover.executeCommand("L");
        assertEquals("N", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverRightNorthToEast() throws Exception {
        setUpRover(1, 2, "N");
        rover.executeCommand("R");
        assertEquals("E", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverRightNorthEastToSouth() throws Exception {
        setUpRover(1, 2, "E");
        rover.executeCommand("R");
        assertEquals("S", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverRightSouthToWest() throws Exception {
        setUpRover(1, 2, "S");
        rover.executeCommand("R");
        assertEquals("W", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_TurnRoverRightWestToNorth() throws Exception {
        setUpRover(1, 2, "W");
        rover.executeCommand("R");
        assertEquals("N", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardNorthNotOnBoundary() throws Exception {
        setUpRover(1, 2, "N");
        rover.executeCommand("M");
        assertEquals("N", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardSouthNotOnBoundary() throws Exception {
        setUpRover(1, 2, "S");
        rover.executeCommand("M");
        assertEquals("S", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(1, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardEastNotOnBoundary() throws Exception {
        setUpRover(1, 2, "E");
        rover.executeCommand("M");
        assertEquals("E", rover.getDirection());
        assertEquals(2, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardWestNotOnBoundary() throws Exception {
        setUpRover(1, 2, "W");
        rover.executeCommand("M");
        assertEquals("W", rover.getDirection());
        assertEquals(0, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardNorthOnBoundary() throws Exception {
        setUpRover(1, 5, "N");
        rover.executeCommand("M");
        assertEquals("N", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(5, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardSouthOnBoundary() throws Exception {
        setUpRover(1, 0, "S");
        rover.executeCommand("M");
        assertEquals("S", rover.getDirection());
        assertEquals(1, rover.getX());
        assertEquals(0, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardEastOnBoundary() throws Exception {
        setUpRover(5, 2, "E");
        rover.executeCommand("M");
        assertEquals("E", rover.getDirection());
        assertEquals(5, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void test_MoveRoverForwardWestOnBoundary() throws Exception {
        setUpRover(0, 2, "W");
        rover.executeCommand("M");
        assertEquals("W", rover.getDirection());
        assertEquals(0, rover.getX());
        assertEquals(2, rover.getY());
    }

    @Test
    public void testExecuteCommandInvalidCommand() throws Exception {
        setUpRover(2, 2, "W");
        try{
            rover.executeCommand("O");
            fail();
        }catch (RoverCommandNotValidException e){
            assertEquals("O" , e.getInvalidCmd());
        }
    }

    @Test
    public void testSendCommandSequence() throws Exception {
        setUpRover(2, 2, "W");
        rover.sendCommandSequence("MMRRMM");
        assertEquals(2, rover.getX());
        assertEquals(2, rover.getY());
        assertEquals("E", rover.getDirection());
    }

    @Test
    public void test_SendCommandSequenceInvalidCommand() throws Exception {
        setUpRover(0, 2, "W");
        try {
            rover.sendCommandSequence("MB");
            fail();
        }catch (RoverCommandNotValidException e){
            assertEquals("B", e.getInvalidCmd());
        }
    }

}
