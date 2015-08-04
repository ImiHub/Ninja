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
		
		buttons[0] = new Button(Game.WIDTH * Game.SCALE/3, Game.HEIGHT * Game.SCALE/4, 400, 100, "New Game");
		buttons[1] = new Button(Game.WIDTH * Game.SCALE/3, Game.HEIGHT * Game.SCALE/2, 400, 100, "Exit Game");
	}

	public void render (Graphics graphics)
	{
		graphics.drawImage(Game.deathScreenBackground, 0, 0, Game.WIDTH * Game.SCALE + 10, Game.HEIGHT * Game.SCALE + 10, null);
		
		for (int i = 0; i < buttons.length; i++) buttons[i].render(graphics);
	}
}
