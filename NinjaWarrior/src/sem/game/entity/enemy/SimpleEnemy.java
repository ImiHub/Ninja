package sem.game.entity.enemy;

import java.awt.Graphics;
import java.util.Random;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.entity.Entity;
import sem.game.tile.Tile;



public class SimpleEnemy extends Entity
{
	
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate =true;
	private Random rand = new Random();

	public SimpleEnemy(int x, int y, int width, int height, Id id,
			Handler h)
	{
		super(x, y, width, height, id, h);
		int dir = rand.nextInt(2);
		switch(dir)
		{
			case 0:setpX(-1);
			facing=1;
			break;
			case 1:setpX(1);
			facing=0;
			break;
		
		}
	}
	
	
	

	@Override
	public void render(Graphics g)
	{
		if(facing==0)
		{
			g.drawImage(Game.simpleEnemy[frame+3].getBufferedImage(), x, y, width,height,null);
			setpX(-3);
		}
		else if(facing==1)
		{
			g.drawImage(Game.simpleEnemy[frame].getBufferedImage(), x, y, width,height,null);
			setpX(3);
		}
		
	}

	public int r;
	@Override
	public void update()
	{
		r++;
		if(r==4)
		{
			r=0;
			x+=getpX();
			y+=getpY();
		}
		
		
		
		for(Tile t:handler.tile)
		{
			
			if(t.getId()==Id.wall)
			{
				

				
				if(getBottom().intersects(t.getBounds()))
				{
					
					
					
					if(falling) falling=false;
				}
				else if(!falling && !jumping)
				{
					falling=true;
					gravity=0.8;
				}
					
				
				if(getLeft().intersects(t.getBounds()))
				{
					setpX(1);
					facing=1;
				}
				
				if(getRight().intersects(t.getBounds()))
				{
					setpX(-1);
					facing=0;
				}
			}
		}
		
		
		
		
		
		if(falling)
		{
			gravity+=0.1;
			setpY((int)gravity);
		}
		
		
		if(animate)
		{
			frameDelay++;
			if(frameDelay>=40)
			{
				frame++;
				if(frame>=3)
				{
					frame=0;
				}
				frameDelay=0;
			}
		}
	}




	
}
