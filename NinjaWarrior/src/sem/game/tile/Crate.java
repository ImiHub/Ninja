package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;

public class Crate extends Tile
{

	public Crate(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.box, x, y, width, height, null);
		
	}

	@Override
	public void update()
	{
		x+=pX;
		gravitacija();
	}
	
	private void gravitacija()
	{
		
		for(int i=0; handler.tile.size()>i;i++)
		{
			Tile t = handler.tile.get(i);
			
			if(t != this)
			{
				
				
				
				
			}
		}
		



		
	}

}
