package ru.stqa.pft.sandbox;

public class Point {

	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y= y;
	}

	public static double area(Point p1, Point p2) {
		double cathetus1 = p1.x - p2.x;
		double cathetus2 = p1.y - p2.y;

		double distance = Math.sqrt(Math.pow((cathetus1), 2) + Math.pow((cathetus2), 2));
		return distance;
	}
}
