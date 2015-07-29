package sem.game.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.entity.Entity;

public class Bullet extends Tile
{
	private BufferedImage image;
	private int facing;
	
	public Bullet(int x, int y, int width, int height, Id id, Handler h, int facing)
	{
		super(x, y, width, height, id, h);
		this.facing = facing;
		this.pX = facing * 10;
		
		try
		{
			if (facing == 1 || facing == 3) image = ImageIO.read(getClass().getResource("/Kunai.png"));
			else if (facing == -1 || facing == -3) image = ImageIO.read(getClass().getResource("/Kunai_l.png"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics graphics)
	{
		graphics.drawImage(image,x, y, width,height,null);
	}

	@Override
	public void update()
	{
		x += pX;
		
		tileIntersect();
		entityIntersect();
	}

	private void entityIntersect()
	{
		for(int i=0; handler.entity.size()>i;i++)
		{
			Entity e = handler.entity.get(i);
			
			if(e.getId() == Id.simpleEnemy)
			{
				if(e.getBounds().intersects(getBounds()))
				{
					this.die();
					e.die();
					break;
				}
			}
			
		}
		
	}

	private void tileIntersect()
	{
		for(int i=0; handler.tile.size()>i;i++)
		{
			Tile e = handler.tile.get(i);
			
				if(e.getId() != Id.bullet && e.getId() != Id.decoration && e.getId() != Id.coin)
				if(e.getBounds().intersects(getBounds()))
				{
					this.die();
					
					break;
				}
			
			
		}
		
	}
}