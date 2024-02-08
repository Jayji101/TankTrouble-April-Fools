import java.awt.Graphics;
import java.awt.Color;
import java.util.*;
public class Maze {

    private ArrayList<Wall> walls;
    private int loophelper;

    public Maze() {
        walls = new ArrayList<>();
    }

    public void drawMaze(Graphics g) {
        for (int i = 0; i < 140; i++) {
            loophelper = i/10;
            walls.get(i).drawVert(g, (i%10)*90, loophelper * 90);
        }
    }


}
