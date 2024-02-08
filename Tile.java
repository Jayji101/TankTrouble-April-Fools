import java.awt.Graphics;
import java.awt.Color;

public class Tile {

    private Color color;

    public Tile (Color color) {
        this.color = color;
    }

    public void draw(Graphics g, int xc, int yc) {
        g.setColor(color);
        g.fillRect(xc, yc, 90, 90);
    }
}
