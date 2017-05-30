package pacman;

public class Position {
    public int x, y;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}