import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.util.*;

public class GreenTank {
    private int x;
    private int xcent;
    private int ycent;
    private int y;
    private int width = 30;
    private int height = 45;
    private ArrayList<ImageIcon> tanks;
    private int dir = 0;
    private double vel;

    private boolean drawn = false;

    public GreenTank(int xc, int yc) {
        x = xc;
        y = yc;
        tanks = new ArrayList<ImageIcon>();

    }

    public void setVel(double vel) {
        this.vel = vel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void tick() {

    }

    public void addTanks() {
        tanks.add(new ImageIcon("Player 2 Images/greentank.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank10deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank20deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank30deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank40deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank50deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank60deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank70deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank80deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank90deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank100deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank110deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank120deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank130deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank140deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank150deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank160deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank170deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank180deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank190deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank200deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank210deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank220deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank230deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank240deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank250deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank260deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank270deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank280deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank290deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank300deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank310deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank320deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank330deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank340deg.png"));
        tanks.add(new ImageIcon("Player 2 Images/greentank350deg.png"));
    }

    public void drawTank(Graphics g) {
        drawn = true;
        if (dir == 0 || dir == 18) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width, height, null);
        } else if (dir == 9 || dir == 27) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 9, height - 10, null);
        }
        // generic 4 way tanks

        else if (dir == 1 || dir == 19) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 6, height + 4, null);
        } else if (dir == 2 || dir == 20) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 12, height + 8, null);
        } else if (dir == 3 || dir == 21) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 17, height + 9, null);
        } else if (dir == 4 || dir == 22) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 20, height + 10, null);
        } else if (dir == 5 || dir == 23) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 21, height + 10, null);
        } else if (dir == 6 || dir == 24) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 19, height + 9, null);
        } else if (dir == 7 || dir == 25) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 17, height + 5, null);
        } else if (dir == 8 || dir == 26) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 15, height - 2, null);
        }
        // draw upside down

        else if (dir == 10 || dir == 28) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 15, height - 2, null);
        } else if (dir == 11 || dir == 29) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 17, height + 5, null);
        } else if (dir == 12 || dir == 30) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 19, height + 9, null);
        } else if (dir == 13 || dir == 31) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 21, height + 10, null);
        } else if (dir == 14 || dir == 32) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 20, height + 10, null);
        } else if (dir == 15 || dir == 33) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 17, height + 9, null);
        } else if (dir == 16 || dir == 34) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 12, height + 7, null);
        } else if (dir == 17 || dir == 35) {
            g.drawImage(tanks.get(dir).getImage(), x, y, width + 6, height + 4, null);
        }

    }

    public void turnLeft(Graphics g) {
        if (drawn) {
            if (dir == 0) {
                dir = 35;
            } else {
                dir--;
            }

            drawTank(g);
        }

    }

    public void turnRight(Graphics g) {

        if (drawn) {
            if (dir == 35) {
                dir = 0;
            } else {
                dir++;
            }

            drawTank(g);

        }
    }

    public void moveForward(Graphics g) {
        if (dir == 0) {
            y -= vel;
        } else if (dir == 9) {
            x += vel;
        } else if (dir == 18) {
            y += vel;
        } else if (dir == 27) {
            x -= vel;
            // angled portions next
            // y portions positive

        } else if (dir == 1 || dir == 35) {
            y -= (vel - 1);
        } else if (dir == 2 || dir == 34) {
            y -= (vel - 2);
        } else if (dir == 3 || dir == 33) {
            y -= (vel - 3);
        } else if (dir == 4 || dir == 32) {
            y -= (vel - 4);
        } else if (dir == 5 || dir == 31) {
            y -= (vel - 5);
        } else if (dir == 6 || dir == 30) {
            y -= (vel - 6);
        } else if (dir == 7 || dir == 29) {
            y -= (vel - 7);
        } else if (dir == 8 || dir == 28) {
            y -= (vel - 8);
            // y portion negative

        } else if (dir == 10 || dir == 26) {
            y += (vel - 8);
        } else if (dir == 11 || dir == 25) {
            y += (vel - 7);
        } else if (dir == 12 || dir == 24) {
            y += (vel - 6);
        } else if (dir == 13 || dir == 23) {
            y += (vel - 5);
        } else if (dir == 14 || dir == 22) {
            y += (vel - 4);
        } else if (dir == 15 || dir == 21) {
            y += (vel - 3);
        } else if (dir == 16 || dir == 20) {
            y += (vel - 2);
        } else if (dir == 17 || dir == 19) {
            y += (vel - 1);
        }
        // x portions positive

        if (dir == 8 || dir == 10) {
            x += (vel - 1);
        } else if (dir == 7 || dir == 11) {
            x += (vel - 2);
        } else if (dir == 6 || dir == 12) {
            x += (vel - 3);
        } else if (dir == 5 || dir == 13) {
            x += (vel - 4);
        } else if (dir == 4 || dir == 14) {
            x += (vel - 5);
        } else if (dir == 3 || dir == 15) {
            x += (vel - 6);
        } else if (dir == 2 || dir == 16) {
            x += (vel - 7);
        } else if (dir == 1 || dir == 17) {
            x += (vel - 8);
            // x portion negative

        } else if (dir == 28 || dir == 26) {
            x -= (vel - 1);
        } else if (dir == 29 || dir == 25) {
            x -= (vel - 2);
        } else if (dir == 30 || dir == 24) {
            x -= (vel - 3);
        } else if (dir == 31 || dir == 23) {
            x -= (vel - 4);
        } else if (dir == 32 || dir == 22) {
            x -= (vel - 5);
        } else if (dir == 33 || dir == 21) {
            x -= (vel - 6);
        } else if (dir == 34 || dir == 20) {
            x -= (vel - 7);
        } else if (dir == 35 || dir == 19) {
            x -= (vel - 8);
        }

    }

    public void moveBackward(Graphics g) {
        if (dir == 0) {
            y += vel;
        } else if (dir == 9) {
            x -= vel;
        } else if (dir == 18) {
            y -= vel;
        } else if (dir == 27) {
            x += vel;
            // angled portions next
            // y portions positive

        } else if (dir == 1 || dir == 35) {
            y += (vel - 1);
        } else if (dir == 2 || dir == 34) {
            y += (vel - 2);
        } else if (dir == 3 || dir == 33) {
            y += (vel - 3);
        } else if (dir == 4 || dir == 32) {
            y += (vel - 4);
        } else if (dir == 5 || dir == 31) {
            y += (vel - 5);
        } else if (dir == 6 || dir == 30) {
            y += (vel - 6);
        } else if (dir == 7 || dir == 29) {
            y += (vel - 7);
        } else if (dir == 8 || dir == 28) {
            y += (vel - 8);
            // y portion negative

        } else if (dir == 10 || dir == 26) {
            y -= (vel - 8);
        } else if (dir == 11 || dir == 25) {
            y -= (vel - 7);
        } else if (dir == 12 || dir == 24) {
            y -= (vel - 6);
        } else if (dir == 13 || dir == 23) {
            y -= (vel - 5);
        } else if (dir == 14 || dir == 22) {
            y -= (vel - 4);
        } else if (dir == 15 || dir == 21) {
            y -= (vel - 3);
        } else if (dir == 16 || dir == 20) {
            y -= (vel - 2);
        } else if (dir == 17 || dir == 19) {
            y -= (vel - 1);
        }
        // x portions positive

        if (dir == 8 || dir == 10) {
            x -= (vel - 1);
        } else if (dir == 7 || dir == 11) {
            x -= (vel - 2);
        } else if (dir == 6 || dir == 12) {
            x -= (vel - 3);
        } else if (dir == 5 || dir == 13) {
            x -= (vel - 4);
        } else if (dir == 4 || dir == 14) {
            x -= (vel - 5);
        } else if (dir == 3 || dir == 15) {
            x -= (vel - 6);
        } else if (dir == 2 || dir == 16) {
            x -= (vel - 7);
        } else if (dir == 1 || dir == 17) {
            x -= (vel - 8);
            // x portion negative

        } else if (dir == 28 || dir == 26) {
            x += (vel - 1);
        } else if (dir == 29 || dir == 25) {
            x += (vel - 2);
        } else if (dir == 30 || dir == 24) {
            x += (vel - 3);
        } else if (dir == 31 || dir == 23) {
            x += (vel - 4);
        } else if (dir == 32 || dir == 22) {
            x += (vel - 5);
        } else if (dir == 33 || dir == 21) {
            x += (vel - 6);
        } else if (dir == 34 || dir == 20) {
            x += (vel - 7);
        } else if (dir == 35 || dir == 19) {
            x += (vel - 8);
        }
    }

    public int getDir() {
        return dir;
    }

    public void setCenter() {
        xcent = x + width / 2;
        ycent = y + height / 2;
    }

    public int getXcent() {
        return xcent;
    }

    public int getYcent() {
        return ycent;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDir(int direction) {
        dir = direction;
    }
}
    public void setDir(int direction) {
        dir = direction;
    }
}

