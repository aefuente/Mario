/*

Name: Andre Fuentes
Date: 9/22/20
Description: This is the controller file that reads all events and passes that information along

 */

import java.awt.event.*;


class Controller implements ActionListener, MouseListener, KeyListener
{
	Model model;
	View view;
	// Creates boolean keystroke variables
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacekey;
	boolean cntrlkey;
	boolean skey;
	boolean lkey;
	boolean hldcntrl;


	Controller(Model m, View v)
	{
		model = m;
		view = v;
		hldcntrl = false;
	}

	public void actionPerformed(ActionEvent e) { }

	// Detects mouse clicks and adjusts for scroll position
	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) { }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacekey = true; break;
			case KeyEvent.VK_CONTROL: cntrlkey = true; break;
			case KeyEvent.VK_S: skey = true; break;
			case KeyEvent.VK_L: lkey = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: spacekey = false; break;
			case KeyEvent.VK_CONTROL: cntrlkey = false; break;
			case KeyEvent.VK_S: skey = false; break;
			case KeyEvent.VK_L: lkey = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{

		// Moves mario to the right and animates mario
		if(keyRight) {
			model.marioMoveRight();
		}

		// Moves mario to the left and animates mario
		if(keyLeft) {
			model.marioMoveLeft();
		}

		// Shoot the fireboy only ones
		if (cntrlkey && !hldcntrl){
			model.createFireBall();
		}
		hldcntrl = cntrlkey;
		// Scroll Position is based upon mario position so the camera always stays fixed

		// Makes mario jump
		if(spacekey){
			model.mario.jump();
		}
		//if(skey){model.marshal().save("Map.json");}
		if(lkey){model.setModel(Json.load("Map.json"));}

	}
}
