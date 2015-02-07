package algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Hull extends LinkedList<Point> {
	/** */
	private static final long serialVersionUID = 5208307131618895513L;
	protected Color color;

	public Hull (List<Point> P) {
		super();
		setColor();
		for (Point p : P) 
			this.add(p);

		if (P.size() == 3 && !Point.isLeftTurn(get(0),get(1),get(2)))
			Collections.reverse(this);
	}

	public Hull() { 
		super(); 
		setColor();
	}

	protected void setColor() {
		color = Color.BLACK;
		//color = new Color((int) (Math.random()*256.0), (int) (Math.random()*256.0), (int) (Math.random()*256.0));
	}

	public static Hull merge(TangentReceiving task, Hull left, Hull right) {
		Hull result = new Hull();

		Tangent lower = Hull.computeLowerTangent(task, left, right);
		Tangent upper = Hull.computeUpperTangent(task, left, right);

		boolean first1 = upper.left != lower.left, first2 = lower.right != upper.right;
		for (int i = upper.left; i != lower.left || first1; i = (i+1)%left.size() )  {
			first1 = false;
			result.add(left.get(i));
		}
		result.add(left.get(lower.left));
		for (int i= lower.right; i != upper.right || first2; i = (i+1)%right.size()) {
			first2 = false;
			result.add(right.get(i));
		}
		result.add(right.get(upper.right));

		task.setTangent(null, null,null);
		return result;
	}

	public static Tangent computeUpperTangent(TangentReceiving task, Hull left, Hull right) {
		Tangent tangent = new Tangent(left.indexOf(Collections.max(left)),right.indexOf(Collections.min(right)), Color.BLUE);
		task.setTangent(tangent,left,right);

		while(!left.isUpperTangent(tangent,left,right) || !right.isUpperTangent(tangent,left,right)) {
			while(!left.isUpperTangent(tangent,left,right)) {
				tangent.left = (tangent.left+1)%left.size();
				task.setTangent(tangent,left,right);
			}
			while(!right.isUpperTangent(tangent,left,right)) {
				tangent.right = (tangent.right-1 < 0) ? right.size()-1 : (tangent.right-1);
//				if (tangent.right - 1 < 0) {
//					tangent.right = right.size()-1;
//				} else
//					tangent.right= tangent.right-1;
				task.setTangent(tangent,left,right);
			}
		}

		return tangent;
	}

	public static Tangent computeLowerTangent(TangentReceiving task, Hull left, Hull right) {
		Tangent tangent = new Tangent(left.indexOf(Collections.max(left)),right.indexOf(Collections.min(right)), Color.RED);
		task.setTangent(tangent,left,right);		

		while (!left.isLowerTangent(tangent,left,right) || !right.isLowerTangent(tangent,left,right)) {
			while (!left.isLowerTangent(tangent,left,right)) {
				tangent.left = (tangent.left-1 < 0) ? left.size()-1 : (tangent.left-1);
//				if (tangent.left-1 < 0) {
//					tangent.left = left.size()-1;
//				} else
//					tangent.left = tangent.left-1;
				task.setTangent(tangent,left,right);
			}
			while (!right.isLowerTangent(tangent,left,right)) {
				tangent.right = (tangent.right+1)%right.size();
				task.setTangent(tangent,left,right);
			}
		}

		return tangent;
	}

	public boolean isLowerTangent(Tangent tan, Hull left, Hull right) {
		if (left == this) 
			return !Point.isLeftTurn(right.get(tan.right), left.get(tan.left), left.get((tan.left-1 <0) ? left.size() -1 : (tan.left-1)));
		else 
			return Point.isLeftTurn(left.get(tan.left), right.get(tan.right), right.get((tan.right+1)%right.size()));
	}

	public boolean isUpperTangent(Tangent tan, Hull left, Hull right) {
		if (left == this)
			return Point.isLeftTurn(right.get(tan.right), left.get(tan.left), left.get((tan.left+1)%left.size()));
		else
			return !Point.isLeftTurn(left.get(tan.left), right.get(tan.right), right.get((tan.right-1 < 0)? right.size()-1 : (tan.right-1)));
	}

	public void paint(Graphics g) {
		for (Point p : this) {
			g.setColor(color);
			g.drawOval(p.getX() - 3, p.getY() - 3, 6,6);
		}

		Iterator<Point> iter = this.listIterator();
		Point cur, peek;

		cur = iter.next();
		try { peek = iter.next(); } catch (NoSuchElementException e) { peek = null; }
		while (peek != null) {
			paintEdge(g,cur,peek);
			cur = peek;
			try { peek = iter.next(); } catch (NoSuchElementException e) { peek = null; }
		}
		paintEdge(g,cur,this.getFirst());
	}

	public void paintEdge(Graphics g, Point a, Point b) {
		g.setColor(color);
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
	}

}


