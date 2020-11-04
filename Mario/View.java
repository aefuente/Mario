/*

Name: Andre Fuentes
Date: 9/22/20
Description: This is the view file that handles the graphics inside of a JFrame

 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	// Creates member variables for the button, turtle, and the model
	static BufferedImage ground_image = null;
	Model model;

	// Load an image, ANY image!!
	static BufferedImage loadImage(String filename){
		BufferedImage im = null;
		try{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " has been loaded.");

		}	catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}


	View(Controller c, Model m) {
		model = m;
		// Loads the ground image
		if (ground_image == null) {
			ground_image = loadImage("Ground.png");
		}
	}

	// Creates the blue background and draws the turtle image
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw the Ground
		for (int i = 0; i < 10; i++) {
			g.drawImage(ground_image, i*500 - model.scrollPos, 450, null);
		}
		// Draws all the sprites
		for(int i = 0; i < model.sprites.size(); i++) {
			Sprite S = model.sprites.get(i);
			S.draw(g,model.scrollPos);
		}

	}
}
