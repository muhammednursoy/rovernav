package rover;

public class RoverPosition {
    private int x;
    private int y;
    private String direction;

    public RoverPosition(int x, int y, String direction) {
        //validate direction
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void increaseY() {
        this.y += 1;
    }

    public void decreaseY() {
        this.y -= 1;
    }

    public void increaseX() {
        this.x += 1;
    }

    public void decreaseX() {
        this.x -= 1;
    }
}
