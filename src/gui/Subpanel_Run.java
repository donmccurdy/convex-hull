package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import algorithm.Program;

public class Subpanel_Run extends JPanel {
	/** */
	private static final long serialVersionUID = 5544516401625979275L;
	private JButton run, pause, reset;
	
	public Subpanel_Run(Program p) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setOpaque(false);
		
		p.runPanel = this;
		
		run = new JButton("Run");
		run.setActionCommand("run");
		run.addActionListener(p);
		
		pause = new JButton("Pause");
		pause.setActionCommand("pause");
		pause.addActionListener(p);
		pause.setEnabled(false);
		
		reset = new JButton("Reset");
		reset.setActionCommand("reset");
		reset.addActionListener(p);
		reset.setEnabled(false);
		
		this.add(Box.createGlue());
		this.add(run);
		this.add(Box.createHorizontalStrut(50));
		this.add(pause);
		this.add(Box.createHorizontalStrut(50));
		this.add(reset);
		this.add(Box.createGlue());
	}
	
	public void setRunEnabled(boolean on) {
		run.setEnabled(on);
	}
	
	public void setPauseEnabled(boolean on) {
		pause.setEnabled(on);
	}
	
	public void togglePausePlay(boolean pause) {
		if (pause) this.pause.setText("Pause");
		else this.pause.setText("Resume");
		this.repaint();
	}
	
	public void setResetEnabled(boolean on) {
		reset.setEnabled(on);
	}
	
	
}
