/*

Name: Andre Fuentes
Date: 9/22/20
Description: This is the main game file that creates all of the instances and strings all of the
code together

 */
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	// Creates member variables
	Model model;
	Controller controller;
	View view;

	public Game()
	{
		// Creates instances of objects for the game
		model = new Model();
		view = new View(controller, model);

		controller = new Controller(model,view);

		this.addKeyListener(controller);
		view.addMouseListener(controller);
		this.setTitle("Mario");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// Updates the game
	public void run()
	{
		while(true)
		{

			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			controller.update();

			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 miliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	// Starts the game
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
