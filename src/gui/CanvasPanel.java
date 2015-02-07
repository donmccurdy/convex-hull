package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithm.Hull;
import algorithm.Point;
import algorithm.Program;

public class CanvasPanel extends BackgroundPanel implements MouseListener, ChangeListener {
	/***/
	private static final long serialVersionUID = 7066451154514936420L;
	private Program program;
	
	public CanvasPanel (Program p) {
		program = p;
		//this.setBackground("old_paper.jpg");
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		p.addChangeListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Point p : program.getPoints()) {
			p.paint(g);
		}
		for (Hull h : program.getHulls())
			h.paint(g);
		if (program.tangent != null)
			program.tangent.paint(g);
	}
	
	
	////////////////////////////////////////////////////////////////////////
	//																			MOUSE LISTENER METHODS
	////////////////////////////////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Event at " + arg0.getX() + ", " + arg0.getY());
		program.addPoint(new Point(arg0.getX(),arg0.getY()));
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////////////////
	//																 		  CHANGE LISTENER METHODS
	////////////////////////////////////////////////////////////////////////
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.repaint();
		//System.out.println("Canvas state updated.");
	}



}
