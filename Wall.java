import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

public class Wall {

    private int loophelper;
    private int x, y, width, height, radius;
    private int xcent, ycent;
    private boolean vertdrawn = false;
    private boolean horizdrawn = false;

    public Wall() {
    }

    public void drawVert(Graphics g, int xc, int yc) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xc, yc, 10, 90);
        x = xc;
        y = yc;
        width = 10;
        height = 90;
        horizdrawn = false;
        vertdrawn = true;
    }

    public void drawHoriz(Graphics g, int xc, int yc) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xc, yc, 90, 10);
        x = xc;
        y = yc;
        width = 90;
        height = 10;
        vertdrawn = false;
        horizdrawn = true;
    }

    public double findDistanceFrom(double xc, double yc) {
        return Math.sqrt(Math.pow(x - xc, 2) + Math.pow(y - yc, 2));
    }

    public void vertIntersectsWithTank(RedTank tank) {
        if (vertdrawn) {
            radius = width;
            if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

                if (tank.getDir() <= 8 && tank.getDir() >= 0) {
                    tank.setDir(0);
                } else if (tank.getDir() == 9 || tank.getDir() == 27) {
                    tank.setVel(0);
                } else if (tank.getDir() <= 18 && tank.getDir() >= 10) {
                    tank.setDir(18);
                } else if (tank.getDir() <= 26 && tank.getDir() >= 19) {
                    tank.setDir(18);
                } else if (tank.getDir() <= 35 && tank.getDir() >= 28) {
                    tank.setDir(0);
                }

            }
        }
    }

    public void horizIntersectsWithTank(RedTank tank) {
        if (horizdrawn) {
            radius = height;
            if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

                if (tank.getDir() <= 8 && tank.getDir() >= 1) {
                    tank.setDir(9);
                } else if (tank.getDir() == 0 || tank.getDir() == 18) {
                    tank.setVel(0);
                } else if (tank.getDir() <= 17 && tank.getDir() >= 10) {
                    tank.setDir(9);
                } else if (tank.getDir() <= 27 && tank.getDir() >= 19) {
                    tank.setDir(27);
                } else if (tank.getDir() <= 35 && tank.getDir() >= 28) {
                    tank.setDir(27);
                }

            }
        }
    }

    public void gvertIntersectsWithTank(GreenTank tank) {
        if (vertdrawn) {
            radius = width;
            if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

                if (tank.getDir() <= 8 && tank.getDir() >= 0) {
                    tank.setDir(0);
                } else if (tank.getDir() == 9 || tank.getDir() == 27) {
                    tank.setVel(0);
                } else if (tank.getDir() <= 18 && tank.getDir() >= 10) {
                    tank.setDir(18);
                } else if (tank.getDir() <= 26 && tank.getDir() >= 19) {
                    tank.setDir(18);
                } else if (tank.getDir() <= 35 && tank.getDir() >= 28) {
                    tank.setDir(0);
                }

            }
        }
    }

    public void ghorizIntersectsWithTank(GreenTank tank) {
        if (horizdrawn) {
            radius = height;
            if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

                if (tank.getDir() <= 8 && tank.getDir() >= 1) {
                    tank.setDir(9);
                } else if (tank.getDir() == 0 || tank.getDir() == 18) {
                    tank.setVel(0);
                } else if (tank.getDir() <= 17 && tank.getDir() >= 10) {
                    tank.setDir(9);
                } else if (tank.getDir() <= 27 && tank.getDir() >= 19) {
                    tank.setDir(27);
                } else if (tank.getDir() <= 35 && tank.getDir() >= 28) {
                    tank.setDir(27);
                }

            }
        }
    }

    public boolean getVertDrawn() {
        return vertdrawn;
    }
    public boolean getHorizDrawn() {
        return horizdrawn;
    }

    public void setCenter() {
        xcent = x + width/2;
        ycent = y + height/2;
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

}