package rover;

public class RoverMap {
    private final int x;
    private final int y;

    public RoverMap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getMaxX() {
        return x;
    }

    public int getMaxY() {
        return y;
    }
}
