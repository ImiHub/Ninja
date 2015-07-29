package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.gfx.Sprite;
import sem.game.tile.Tile;

public class Water extends Tile
{

	int boja=0;
	private Sprite slika;
	
	public Water(int x, int y, int width, int height, Id id, Handler h, int boja)
	{
		super(x, y, width, height, id, h);
		this.boja=boja;
		slika = new Sprite(Game.sheet64,boja,1);
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(slika.getBufferedImage(), x, y, width,height,null);
	}

	@Override
	public void update()
	{
		
	}

}
