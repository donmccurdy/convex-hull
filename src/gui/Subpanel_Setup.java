package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import algorithm.Program;

public class Subpanel_Setup extends JPanel {

	/***/
	private static final long serialVersionUID = 4669495117684358642L;

	public Subpanel_Setup (Program p) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setOpaque(false);
		
		JButton randomize = new JButton("Randomize");
		randomize.setActionCommand("rando");
		randomize.addActionListener(p);
		
		JButton clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(p);
		
		this.add(Box.createGlue());
		this.add(randomize);
		this.add(Box.createHorizontalStrut(50));
		this.add(clear);
		this.add(Box.createGlue());
	}
	
}
