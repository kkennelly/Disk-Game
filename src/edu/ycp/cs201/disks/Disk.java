package edu.ycp.cs201.disks;

/**
 * An instance of the Disk class represents a disk
 * to be placed on the game board.
 */
public class Disk {

	private double xCor;
	private double yCor;
	private double radius;
	private DiskColor color;

	public Disk(double x, double y, double radius, DiskColor color) 
	{
		xCor = x;
		yCor = y;
		this.radius = radius;
		this.color = color;
	}
	
	/**
	 * @return the Disk's x coordinate
	 */
	public double getX()
	{
		return xCor;
	}
	
	/**
	 * @return the Disk's y coordinate
	 */
	public double getY() 
	{
		return yCor;
	}
	
	/**
	 * @return the Disk's radius
	 */
	public double getRadius() 
	{
		return radius;
	}
	
	/**
	 * @return the Disk's color
	 */
	public DiskColor getColor() 
	{
		return color;
	}
	
	/**
	 * Return true if this Disk overlaps
	 * the Disk given as the parameter, false otherwise.
	 * 
	 * @param other another Disk
	 * @return true if the two Disks overlap, false if they don't
	 */
	public boolean overlaps(Disk other) 
	{
		double distance = Math.sqrt(Math.pow(xCor - other.getX(), 2) + Math.pow(yCor - other.getY(), 2));
		
		if(distance <= (radius + other.getRadius()))
			return true;
		return false;
	}

	/**
	 * Return true if this Disk is out of bounds, meaning that
	 * it is not entirely enclosed by rectangle whose width and
	 * height are given by the two parameters. 
	 * Assume that the upper-left hand corner of the rectangle
	 * is at (0,0), that x coordinates increase going to
	 * the right, and that y coordinates increase going down.
	 * 
	 * @param width   the width of a rectangle
	 * @param height  the height of a rectangle
	 * @return false if the Disk fits entirely within the rectangle,
	 *         true if at least part of the Disk lies outside the
	 *         rectangle
	 */
	public boolean isOutOfBounds(double width, double height)
	{
		if(xCor + radius > width)
			return true;
		else if (xCor - radius < 0)
			return true;
		else if (yCor + radius > height)
			return true;
		else if (yCor - radius < 0)
			return true;
		
		return false;
	}
}
