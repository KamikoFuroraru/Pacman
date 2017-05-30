package pacman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Move {

    Position pos;
    int curDir, reqDir;
    private static Random random = new Random();
    private static Labyrinth labyrinth = new Labyrinth();
    private static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;
    private static final int[] dy = {0, -1, 0, 1};
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] revDir = {RIGHT, DOWN, LEFT, UP};

    public  Move(Position pos) {
        this.pos = new Position(pos.y, pos.x);
    }

    private static int wrap(int value, int d, int max) {
        return (value + d + max) % max;
    }

    public boolean move(int reqDir, Move move) {
        int x = move.pos.x;
        int y = move.pos.y;
        int newY = wrap(y, dy[reqDir], labyrinth.getRows());
        int newX = wrap(x, dx[reqDir], labyrinth.getColumns());
        if (labyrinth.charAt(newY, newX) != '0') {
            move.pos.y = newY;
            move.pos.x = newX;
            return true;
        }
        return false;
    }

    public static int[] decide(Move[] ghosts) {
        int [] dirs = new int[4];
        for (int i = 0; i < 4; i++) {
            List<Integer> list = getPossibleDirs(ghosts[i].pos);
            list.remove(new Integer(revDir[ghosts[i].curDir]));
            dirs[i] = list.get(random.nextInt(list.size()));
        }
        return dirs;
    }

    public static List<Integer> getPossibleDirs(Position pos) {
        List<Integer> list = new ArrayList<Integer>();
        for (int d = 0; d < 4; d++) {
            Position newPos = getNextPositionInDir(pos, d);
            if (labyrinth.charAt(newPos.y, newPos.x) != '0') {
                list.add(d);
            }
        }
        return list;
    }

    private static Position getNextPositionInDir(Position pos, int d) {
        int newY = wrap(pos.y, dy[d], labyrinth.getRows());
        int newX = wrap(pos.x, dx[d], labyrinth.getColumns());
        return new Position(newY, newX);
    }


    public boolean touching(Position a, Position b) {
        return Math.abs(a.y - b.y) + Math.abs(a.x - b.x) < 18;
    }
}
