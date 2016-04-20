package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

	@Test
	public void testArea(){
		Point p1 = new Point(3,7);
		Point p2 = new Point(6,3);
		Assert.assertEquals(Point.area(p1, p2),5.0);
	}

	@Test
	public void testArea2(){
		Point p1 = new Point(1, 12);
		Point p2 = new Point(7, 20);
		Assert.assertEquals(Point.area(p1, p2), 10.0);
	}
}
