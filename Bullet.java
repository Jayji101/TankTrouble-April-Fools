import java.awt.Graphics;
import java.awt.Color;

public class Bullet extends Ball {

    private double x, y, xSpeed, ySpeed, xtwo, ytwo;

    private double radius = 4;

    private int size = 8;

    private int tankSize;

    ;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        xtwo = x - radius;
        ytwo = y - radius;
        g.setColor(Color.BLACK);
        g.fillOval((int) xtwo, (int) ytwo, size, size);
    }

    public void setSpeed(double xSpeed, double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void move(int rightEdge, int bottomEdge) {

        if (x + radius >= rightEdge) {
            xSpeed = xSpeed *= -1;
        }
        if (x - radius <= 0) {
            xSpeed = xSpeed *= -1;
        }
        if (y + radius >= bottomEdge) {
            ySpeed = ySpeed *= -1;
        }
        if (y - radius <= 0) {
            ySpeed = ySpeed *= -1;
        }
        y += ySpeed;
        x += xSpeed;

    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /*
     * public double findDistanceFrom(double xc, double yc) {
     * return Math.sqrt(Math.pow(getX() - xc, 2) + Math.pow(getY() - yc, 2));
     * }
     * 
     * public boolean intersectsWith(RedTank tank) {
     * if (findDistanceFrom(tank.getX(), tank.getY()) <= tank.getWidth() +
     * getRadius()) {
     * return true;
     * } else {
     * return false;
     * }
     * }
     */

    public double findDistanceFrom(double xc, double yc) {
        return Math.sqrt(Math.pow(x - xc, 2) + Math.pow(y - yc, 2));
    }

    public boolean collides(RedTank tank) {

        if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

            if (xSpeed < -1.4 || xSpeed > 1.4) {
                if (tank.getDir() <= 13 && tank.getDir() >= 5) {
                    tankSize = tank.getHeight();
                }
                if (tank.getDir() <= 31 && tank.getDir() >= 23) {
                    tankSize = tank.getHeight();
                }
            } else if (ySpeed < -1.4 || ySpeed > 1.4) {
                if (tank.getDir() <= 4 && tank.getDir() >= 32) {
                    tankSize = tank.getWidth();
                }
                if (tank.getDir() <= 22 && tank.getDir() >= 14) {
                    tankSize = tank.getWidth();
                }
            }

        }
        return findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tankSize + radius;
    }

    public boolean gollides(GreenTank tank) {

        if (findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tank.getHeight() + radius) {

            if (xSpeed < -1.4 || xSpeed > 1.4) {
                if (tank.getDir() <= 13 && tank.getDir() >= 5) {
                    tankSize = tank.getHeight();
                }
                if (tank.getDir() <= 31 && tank.getDir() >= 23) {
                    tankSize = tank.getHeight();
                }
            } else if (ySpeed < -1.4 || ySpeed > 1.4) {
                if (tank.getDir() <= 4 && tank.getDir() >= 32) {
                    tankSize = tank.getWidth();
                }
                if (tank.getDir() <= 22 && tank.getDir() >= 14) {
                    tankSize = tank.getWidth();
                }
            }

        }
        return findDistanceFrom(tank.getXcent(), tank.getYcent()) <= tankSize + radius;
    }

    public boolean intersectsWithVert(Wall wall) {

        if (wall.getVertDrawn()) {

            if (findDistanceFrom(wall.getXcent(), wall.getYcent()) <= wall.getWidth() + radius) {
                return true;
            } else {
                return false;
            }

        }
        else {
            return false;
        }
    }

    public boolean intersectsWithHoriz(Wall wall) {

        if (wall.getHorizDrawn()) {

            if (findDistanceFrom(wall.getXcent(), wall.getYcent()) <= wall.getHeight() + radius) {
                return true;
            } else {
                return false;
            }

        }
        else {
            return false;
        }

    }

    /*
     * public boolean collisionDectection() {
     * var collided = false;
     * //case 1: the center of the circle c1 is inside the rect 1
     * if(r1.contains(c1.center.x.toInt(), c1.center.y.toInt())){
     * collided = true;
     * } else {
     * //case 2: the center is outside the rect1 r1
     * var pEdge = PointF()
     * //Update x coordinate of pEdge
     * if(c1.center.x < r1.left) {
     * pEdge.x = r1.left.toFloat()
     * } else if(c1.center.x > r1.right) {
     * pEdge.x = r1.right.toFloat()
     * } else {
     * pEdge.x = c1.center.x
     * }
     * //Update x coordinate of pEdge
     * if(c1.center.y < r1.top) {
     * pEdge.y = r1.top.toFloat()
     * } else if(c1.center.y > r1.bottom) {
     * pEdge.y = r1.bottom.toFloat()
     * } else{
     * pEdge.y = c1.center.y
     * }
     * val deltaX = c1.center.x - pEdge.x
     * val deltaY = c1.center.y - pEdge.y
     * val distance = Math.sqrt((deltaX*deltaX + deltaY*deltaY).toDouble())
     * collided = (distance <= c1.radius)
     * }
     * if(collided) {
     * //Collision detected here
     * } else {
     * //do sth else
     * }
     * }
     * 
     * public boolean collides(RedTank rect) {
     * circleDistanceX = (int) Math.abs(xcent - rect.getXcent());
     * circleDistanceY = (int) Math.abs(ycent - rect.getYcent());
     * 
     * if (circleDistanceX > (rect.getWidth() / 2 + radius)) {
     * return false;
     * }
     * if (circleDistanceY > (rect.getHeight() / 2 + radius)) {
     * return false;
     * }
     * 
     * if (circleDistanceX <= (rect.getWidth() / 2)) {
     * return true;
     * }
     * if (circleDistanceY <= (rect.getHeight() / 2)) {
     * return true;
     * }
     * 
     * cornerDistance_sq = Math.sqrt(circleDistanceX - rect.getWidth()/2) +
     * Math.sqrt(circleDistanceY - rect.getHeight()/2);
     * 
     * return (cornerDistance_sq <= (Math.sqrt(radius)));
     * }
     */
}