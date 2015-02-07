package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.Bin;

public class BackgroundPanel extends JPanel {
	/** */
	private static final long serialVersionUID = -4807024496735273733L;
	Image backgroundImage = null;
	Color backgroundColor = Color.white;
	
	
	protected void setBackground(String s) {
		try { 
			URL imageURL = Bin.getResourceClass().getResource(s);
			backgroundImage = ImageIO.read(imageURL);	
			this.setOpaque(false);
		}
		catch (Exception e) { System.out.println("Background image not loaded: " + e); this.setBackground(backgroundColor); }
	}
	
	@Override
	public void paint(Graphics g) {
		if (backgroundImage != null) 
			g.drawImage(backgroundImage, 0,0, this.getWidth(), this.getHeight(), this);

		super.paint(g);
	}
	
}
