package sem.game.tile;

import java.awt.Graphics;
import sem.game.Handler;
import sem.game.Id;
import sem.game.gfx.Sprite;
import sem.game.gfx.SpriteSheet;

public class Coin extends Tile
{
	private int frame = 1;
	private int uspori = 0;
	private Sprite image;
	private SpriteSheet sheetNew = new SpriteSheet("/coins_g.png", 64, 64);
	
	public Coin(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		image = new Sprite(sheetNew, frame, 1);
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image.getBufferedImage(), x, y, width, height,null);
	}

	@Override
	public void update()
	{
		if (frame == 17) frame = 1;
		
		image = new Sprite(sheetNew, frame, 1);
		if(uspori < 7)
		{
			uspori++;
		}
		else
		{
			uspori=0;
			frame ++;
		}
		
	}
}
