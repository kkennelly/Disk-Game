package edu.ycp.cs201.disks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DisksPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final Color BARCOLOR = new Color(255, 23, 23, 63);
	private static final int MAXRADIUS = 44;
	private static final int MINRADIUS = 10;
	private static final int TOTALTIME = 100;
	
	private Timer timer;
	private ArrayList<Disk> theArray;
	private int curRadius;
	private Random rand;
	private int xCor;
	private int yCor;
	private int timeLeft;
	private boolean gameOver;
	
	
	public DisksPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.GRAY);
		
		theArray = new ArrayList<Disk> ();
		rand = new Random();
		xCor = 0;
		yCor = 0;
		curRadius = createRadius();
		timeLeft = TOTALTIME;
		gameOver = false;
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				handleMouseMove(e);
			}
		});
		
		// Schedule timer events to fire every 100 milliseconds (0.1 seconds)
		this.timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerEvent(e);
			}
		});
		
		timer.start();
	}

	// You can call this method to convert a DiskColor value into
	// a java.awt.Color object.
	public Color colorOf(DiskColor dc) 
	{
		return new Color(dc.red(), dc.green(), dc.blue());
	}

	// This method is called whenever the mouse is moved
	protected void handleMouseMove(MouseEvent e) 
	{
		xCor = e.getX();
		yCor = e.getY();
		
		repaint();
	}
	
	// This method is called whenever the mouse is clicked
	protected void handleMouseClick(MouseEvent e) 
	{
		//if the game is not over then it will proceed and instantiate a Disk object appropriately
		if(gameOver == false)
		{
			Disk theDisk = new Disk(e.getX(), e.getY(), curRadius, createColor());
		
			//if the placement of the Disk is illegal then the game is over.
			//if the Disk is legal then the Disk is added to the ArrayList and the timer restarts
			if(theDisk.isOutOfBounds(WIDTH, HEIGHT) == false && checkOverlaps(theDisk) == false)
			{
				theArray.add(theDisk);
			
				timer.stop();
				timer.start();
				
				timeLeft = decreaseTime();
				
				curRadius = createRadius();
			}
			else
				gameOver = true;
			
			if(theArray.size() == 7) {
				timer.stop();
			}
		}
		
		repaint();
	}
	
	// This method is called whenever a timer event fires
	protected void handleTimerEvent(ActionEvent e) 
	{		
		timeLeft = timeLeft - 1;
		
		//if the player ran out of time, the the game is over
		if(timeLeft == 0)
		{
			gameOver = true;
		}
		
		repaint();
	}
	
	//@return a random int within the minimum and maximum requirements
	private int createRadius()
	{
		return rand.nextInt(MAXRADIUS - MINRADIUS) + MINRADIUS;
	}
	
	//@returns a random DiskColor
	private DiskColor createColor()
	{
		int randnum = rand.nextInt(DiskColor.values().length);
		return (DiskColor.values())[randnum];
	}
	
	//@return true if this Disk overlaps with any other disk in the panel
	//@return false if this Disk does not overlap with any other disk in the panel
	private boolean checkOverlaps(Disk theDisk)
	{
		//This loop goes through the ArrayList of disks and checks overlapping
		for(Disk curDisk : theArray)
		{
			if(theDisk.overlaps(curDisk))
				return true;
		}
		return false;
	}
	
	//@return the amount of time left for the player to place the next disk.
	//the return amount increases as the amount of disks increase, until
	//the amount of disks is 15, then the value returned is 25
	private int decreaseTime()
	{
		if(theArray.size() > 15)
			return TOTALTIME - (15 * 5);
		return TOTALTIME - (theArray.size() * 5);
	}
	
	private static final Font FONT = new Font("Dialog", Font.BOLD, 24);

	// This method is called automatically whenever the contents of
	// the window need to be redrawned.
	@Override
	public void paintComponent(Graphics g) 
	{
		// Paint the window background 
		super.paintComponent(g);

		//goes through the Disk ArrayList and displays every disk. stops when there are no more Disks in the array
		for(Disk curDisk : theArray)
		{
			g.setColor(colorOf(curDisk.getColor()));
			g.fillOval((int)(curDisk.getX() - curDisk.getRadius()), (int)(curDisk.getY() - curDisk.getRadius()), 
			      (int)curDisk.getRadius() * 2, (int)curDisk.getRadius() * 2);
		}
		
		g.setColor(Color.BLACK);
		g.drawOval(xCor - curRadius, yCor - curRadius, curRadius * 2, curRadius * 2);
		
		g.setFont(FONT);
		g.drawString(" " + theArray.size(), 350, 250);
	

		g.setColor(BARCOLOR);
		
		g.fillRect(0, 250, WIDTH * timeLeft / TOTALTIME, 40);
		
		//if the game is over then draw the String telling the player
		if(gameOver == true)
		{
			g.setColor(Color.BLACK);
			g.drawString("Game Over!", 100, 75);
			timer.stop();
		}
	}
}
