package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;

public class InvBorder extends Tile
{

	public InvBorder(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.invBorder.getBufferedImage(), x, y, width, height, null);
		
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

}
