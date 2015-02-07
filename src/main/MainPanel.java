package main;

import java.awt.Dimension;

import gui.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import algorithm.Program;

public class MainPanel extends JPanel {
	/** */
	private static final long serialVersionUID = 7947738581963776428L;
	JPanel controls, canvas, bar;
	Program program;
	
	public MainPanel () {
		program = new Program();
		
		controls = new ControlPanel(program);
		canvas = new CanvasPanel(program);
		bar = new BarPanel();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		controls.setMaximumSize(new Dimension(640,70));
		controls.setMinimumSize(new Dimension(640,70));
		controls.setPreferredSize(new Dimension(640,70));

		bar.setMaximumSize(new Dimension(640,30));
		bar.setMinimumSize(new Dimension(640,30));
		bar.setPreferredSize(new Dimension(640,30));
		
		canvas.setMaximumSize(new Dimension(640,300));
		canvas.setMinimumSize(new Dimension(640,300));
		canvas.setPreferredSize(new Dimension(640,300));

		
		this.add(controls);
		this.add(canvas);
		this.add(bar);
		
	
	}
	
	
}
