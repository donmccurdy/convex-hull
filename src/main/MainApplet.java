package main;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class MainApplet extends JApplet {

	/** */
	private static final long serialVersionUID = 8552002771324751992L;
	JPanel mainPanel;
	
	@Override
	public void init() {
		mainPanel = new MainPanel();
	}
	
	@Override
	public void start() {
		this.setContentPane(mainPanel);
	}
	
	@Override
	public void stop() {
		
	}
	
}
