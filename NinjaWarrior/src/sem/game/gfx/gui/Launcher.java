package sem.game.gfx.gui;

import java.awt.Color;
import java.awt.Graphics;

import sem.game.Game;

public class Launcher
{
	public Button[] buttons;
	
	public Launcher()
	{
		makeButtons();
	}
	
	private void makeButtons()
	{
		buttons = new Button[2];
		
		buttons[0] = new Button(100, 100, 400, 100, "New Game");
		buttons[1] = new Button(200, 200, 400, 100, "Exit Game");
	}

	public void render (Graphics graphics)
	{
		graphics.setColor(new Color(255, 20, 47));
		graphics.fillRect(0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10);
		
		for (int i = 0; i < buttons.length; i++) buttons[i].render(graphics);
	}
}
