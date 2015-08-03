package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;

public class KunaiAmo extends Tile
{
	public KunaiAmo(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.kunai_coin, x, y, width, height,null);
	}

	@Override
	public void update()
	{
		
	}
}
