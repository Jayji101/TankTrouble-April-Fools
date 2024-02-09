import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	// 1. Declaration of Variables
	
	private double x;
	private double y;
	private double xtwo;
	private double ytwo;
	private double diameter;
	private double radius;
	private Color color;
	private double xSpeed;
	private double ySpeed;
	
	// 2. Create a default constructor
	/**
	 * Default Constructor
	 * Creates a red ball at (0, 0) with a diameter of 25.  
	 * The default speed is 0.
	 */

	public Ball() {
		 x = 0;
		 y = 0;
		 diameter = 25;
		 color = Color.RED;
		 radius = diameter/2.0;
	}


	// 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)
	// and sets the x and y-speed = 0.
	

	public Ball(double ax, double ay, double adiameter, Color aColor) {
		x = ax;
		y = ay;
		diameter = adiameter;
		color = aColor;
		radius = diameter/2.0;
	}




	// 4. Create getters and setters for all private variables
	
	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDiameter() {
		return this.diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
		radius = diameter/2.0;
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		diameter = radius * 2;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getXSpeed() {
		return this.xSpeed;
	}

	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getYSpeed() {
		return this.ySpeed;
	}

	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}	

	// 5. Finish the following methods
	// 6. Test using BouncingBallTester.java
	
	public void draw(Graphics g) {
		xtwo = x - radius;
		ytwo = y - radius;
		g.setColor(color);
		g.fillOval((int) xtwo, (int) ytwo, (int) diameter, (int) diameter);
		
	}
	
	
	
	// 7. Sets the center location of the ball
	
	public void setLocation(double x, double y) {
	this.x = x;
	this.y = y;
		
	}
	
	

	// 8. Generates a speed between maxSpeed
	// and maxSpeed
	public void setRandomSpeed(double maxSpeed) {

		xSpeed = (Math.random()*2*maxSpeed)- maxSpeed;
		ySpeed = (Math.random()*2*maxSpeed)- maxSpeed;
		if (xSpeed == ySpeed) {
			ySpeed = (Math.random()*2*maxSpeed)- maxSpeed;
		}
	}

	
	// 9. Write the move method to make the ball move around the screen
	// and bounce of the edges.
	public void move(int rightEdge, int bottomEdge) {
	
		if (x + radius >= rightEdge) {
			xSpeed = xSpeed *=-1;
		}
		if (x-radius <= 0) {
			xSpeed = xSpeed*=-1;
		}
		if (y + radius >= bottomEdge) {
			ySpeed = ySpeed *=-1;
		}
		if (y - radius <= 0) {
			ySpeed = ySpeed *=-1;
		}
		y+=ySpeed;
		x+=xSpeed;
		
	}
}