package edu.ycp.cs201.disks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiskTest {
	private static final double DELTA = 0.000001;
	
	private static final int WIDTH = 200;
	private static final int HEIGHT = 200;
	
	private Disk aDisk;
	private Disk overlapsADisk;
	private Disk nearLeftEdge;
	private Disk nearBottomEdge;
	private Disk nearTopEdge;
	private Disk nearRightEdge;

	@Before
	public void setUp() throws Exception {
		aDisk = new Disk(100, 100, 20, DiskColor.RED);
		overlapsADisk = new Disk(110, 100, 20, DiskColor.CYAN);
		nearLeftEdge = new Disk(20, 100, 30, DiskColor.GREEN);
		nearBottomEdge = new Disk(100, 195, 10, DiskColor.BLUE);
		nearTopEdge = new Disk(50, 25, 15, DiskColor.BLUE);
		nearRightEdge = new Disk(280, 100, 15, DiskColor.GREEN);
	}
	
	@Test
	public void testGetX() throws Exception {
		assertEquals(100.0, aDisk.getX(), DELTA);
		assertEquals(110.0, overlapsADisk.getX(), DELTA);
		assertEquals(20.0, nearLeftEdge.getX(), DELTA);
		assertEquals(100.0, nearBottomEdge.getX(), DELTA);
		assertEquals(50.0, nearTopEdge.getX(), DELTA);
		assertEquals(280, nearRightEdge.getX(), DELTA);
	}
	
	@Test
	public void testGetY() throws Exception {
		assertEquals(100.0, aDisk.getY(), DELTA);
		assertEquals(100.0, overlapsADisk.getY(), DELTA);
		assertEquals(100.0, nearLeftEdge.getY(), DELTA);
		assertEquals(195.0, nearBottomEdge.getY(), DELTA);
		assertEquals(25.0, nearTopEdge.getY(), DELTA);
		assertEquals(100.0, nearRightEdge.getY(), DELTA);
	}
	
	@Test
	public void testGetRadius() throws Exception {
		assertEquals(20.0, aDisk.getRadius(), DELTA);
		assertEquals(20.0, overlapsADisk.getRadius(), DELTA);
		assertEquals(30.0, nearLeftEdge.getRadius(), DELTA);
		assertEquals(10.0, nearBottomEdge.getRadius(), DELTA);
		assertEquals(15.0, nearTopEdge.getRadius(), DELTA);
		assertEquals(15.0, nearRightEdge.getRadius(), DELTA);
	}
	
	@Test
	public void testGetColor() throws Exception {
		assertEquals(DiskColor.RED, aDisk.getColor());
		assertEquals(DiskColor.CYAN, overlapsADisk.getColor());
		assertEquals(DiskColor.GREEN, nearLeftEdge.getColor());
		assertEquals(DiskColor.BLUE, nearBottomEdge.getColor());
		assertEquals(DiskColor.BLUE, nearTopEdge.getColor());
		assertEquals(DiskColor.GREEN, nearRightEdge.getColor());
	}
	
	@Test
	public void testOverlaps() throws Exception {
		assertTrue(aDisk.overlaps(overlapsADisk));
		assertTrue(overlapsADisk.overlaps(aDisk));
		assertFalse(aDisk.overlaps(nearLeftEdge));
		assertFalse(aDisk.overlaps(nearBottomEdge));
	}
	
	@Test
	public void testIsOutOfBounds() throws Exception {
		assertFalse(aDisk.isOutOfBounds(WIDTH, HEIGHT));
		assertFalse(overlapsADisk.isOutOfBounds(WIDTH, HEIGHT));
		assertTrue(nearLeftEdge.isOutOfBounds(WIDTH, HEIGHT));
		assertTrue(nearBottomEdge.isOutOfBounds(WIDTH, HEIGHT));
	}
}
