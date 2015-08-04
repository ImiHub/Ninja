package sem.game.tile;

import java.awt.Graphics;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.gfx.Sprite;
import sem.game.gfx.SpriteSheet;

public class Coin extends Tile
{
	private int frame = 1;
	private int uspori = 0;
	private Sprite image;
	
	public Coin(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		image = new Sprite(Game.coinsSheet, frame, 1); //novoooooooo
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
		
		image = new Sprite(Game.coinsSheet, frame, 1); // novoooooo
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
