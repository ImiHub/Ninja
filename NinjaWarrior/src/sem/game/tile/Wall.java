package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.gfx.Sprite;

public class Wall extends Tile
{
	int boja=0;
	private Sprite slika;
	public Wall(int x, int y, int width, int height, Id id, Handler h,int b)
	{
		super(x, y, width, height, id, h);
		boja = b;
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
