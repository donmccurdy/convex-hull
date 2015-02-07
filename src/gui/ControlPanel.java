package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import algorithm.Program;

public class ControlPanel extends BackgroundPanel {
	/** */
	private static final long serialVersionUID = -5297185436363755789L;
	protected Program program;
	protected JTabbedPane pane;
	
	public ControlPanel (Program p) {
		program = p;
		p.controlPanel = this;
		
		pane = new JTabbedPane();
		JPanel setup = new Subpanel_Setup(p);
		JPanel run = new Subpanel_Run(p);
		
		pane.add(setup, "Setup");
		pane.add(run, "Run");
		pane.setPreferredSize(new Dimension(630,70));
		this.add(pane);
		
		this.setBackground("640x70.png");
	}

	public void setEnabled(boolean on) {
		pane.setEnabledAt(0, on);
	}

}
