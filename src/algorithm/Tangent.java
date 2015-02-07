package algorithm;

import java.awt.Color;
import java.awt.Graphics;


public class Tangent {
	public int left, right;
	public Point a,b;
	public Color c;
	public Tangent(int left, int right, Color c) {this.left = left; this.right=right; this.c = c;}
	public void paint(Graphics g) {
		if (a == null || b == null ) {
			System.out.println("UNPAINTABLE TANGENT");
			return;
		}
		g.setColor(c);
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
	}
}