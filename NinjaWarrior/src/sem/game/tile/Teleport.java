package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;


public class Teleport extends Tile
{
	private int destX;
	private int destY;
	private boolean active;
	
	public Teleport(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		active=false;
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

	public int getDestX()
	{
		return destX;
	}

	public void setDestX(int destX)
	{
		this.destX = destX;
	}

	public int getDestY()
	{
		return destY;
	}

	public void setDestY(int destY)
	{
		this.destY = destY;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	
}
