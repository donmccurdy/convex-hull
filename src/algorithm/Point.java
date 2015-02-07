package algorithm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Point implements Comparable<Point>{
	private int x, y;

	public Point (int a, int b) { 
		x = a;
		y = b;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	public Component get() {
		return new JLabel("(POINT)");
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(x-5, y-5, 10, 10);
	}

	
	@Override
	public int compareTo(Point arg0) {
		if (this.getX() != arg0.getX()) 
			return this.getX() - arg0.getX();
		else
			return this.getX() - arg0.getX();
	}
	
	public String toString() {
		return "[" + x + ","+y+"]";
	}
	
	/** http://algs4.cs.princeton.edu/91primitives/ */
	public static boolean isLeftTurn(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y) < 0;
		
	}
	
}