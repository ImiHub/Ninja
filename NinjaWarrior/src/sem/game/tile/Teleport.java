package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;

/*
 * Game:
 * 39
 * 112
 * 
 * Handler:
 * 26
 * 76
 * 89
 * 129-151
 * 
 * KeyInput:
 * 54
 * 
 * Player:
 * 257-284
 */

public class Teleport extends Tile
{
	private int destX;
	private int destY;
	
	public Teleport(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
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
}
