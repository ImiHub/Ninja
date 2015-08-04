package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;

public class TeleportButton extends Tile
{

	private boolean pressed;
	
	public TeleportButton(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		pressed=false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.teleport, x, y, width, height, null);

	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	public boolean isPressed()
	{
		return pressed;
	}

	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
	}

	
}
