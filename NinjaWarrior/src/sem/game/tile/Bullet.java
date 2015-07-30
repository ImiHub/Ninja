package sem.game.tile;

import java.awt.Graphics;
import java.util.Iterator;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.entity.Entity;

public class Bullet extends Tile
{
	private int facing;
	private int firstX;
	
	public Bullet(int x, int y, int width, int height, Id id, Handler h, int facing)
	{
		super(x, y, width, height, id, h);
		this.facing = facing;
		this.pX = facing * 10;
		firstX = x;
	}
	
	@Override
	public void render(Graphics graphics)
	{
		//slika iz game
		
		if (facing == 1 || facing == 3) graphics.drawImage(Game.kunai_r,x, y, width,height,null);
		else if (facing == -1 || facing == -3) graphics.drawImage(Game.kunai_l,x, y, width,height,null);
	}

	@Override
	public void update()
	{
		x += pX;
		
		entityIntersect();
		tileIntersect();
		dissapear();
	}

	// za umiranje
	
	private void entityIntersect()
	{
		Iterator<Entity> it = handler.entity.iterator();
		
		while (it.hasNext()) 
		{
		    Entity e = it.next();
		    
		    if (e.getId() == Id.simpleEnemy)
			{
				if (getBounds().intersects(e.getBounds())) 
				{
					die();
					it.remove();
				}
			}
		}
	}
	
	/*
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
		
	}*/
	
	private void tileIntersect()
	{
		for (int i = 0; handler.tile.size()>i;i++)
		{
			Tile e = handler.tile.get(i);
			
				if(e.getId() != Id.bullet && e.getId() != Id.decoration && e.getId() != Id.coin && e.getBounds().intersects(getBounds()))
				{
					die();
					break;
				}
		}
	}
	
	// da ne zivi vecno ako ne udari
	
	private void dissapear()
	{
		if (Math.abs(firstX - x) > 1000) die();
	}
}