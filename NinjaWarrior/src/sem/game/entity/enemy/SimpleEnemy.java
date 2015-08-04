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
	private boolean animate = true;
	private Random rand = new Random();
	
	private boolean shooting = false; //novo
	private int health; // novo + dole geteri i seteri za ove dve

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
		
		
		
		tileIntersect();
		entityIntersect();
		
		
		
		
		
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




	private void entityIntersect()
	{

		for(int i=0; handler.entity.size()>i;i++)
		{
			
			Entity t = handler.entity.get(i);
			
			if (t.getId()==Id.crate)
			{
				
				if(getBottom().intersects(t.getBounds()))
				{
					
					if(falling) falling=false;
				}
				else if(!falling && !jumping)
				{
					y-=1;
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
		
	}




	private void tileIntersect()
	{

		for(int i=0; handler.tile.size()>i;i++)
		{
			
			Tile t = handler.tile.get(i);
			
			if(t.getId()==Id.wall )
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
		
	}

	//novoovoooo
	
	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}	
}
