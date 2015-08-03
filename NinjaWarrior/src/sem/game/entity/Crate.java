package sem.game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.tile.Small;
import sem.game.tile.Tile;

public class Crate extends Entity
{
	public static int fid=1;
	public int number;
	public Crate(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		number=fid++;
		falling=true;
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
		y+=pY;
		gravitacija();
		tileIntersect();
	}
	
	private void tileIntersect()
	{
		for(int i=0;handler.tile.size()>i;i++)
		{
			Tile t= handler.tile.get(i);
			
			if(t.getId() != Id.smallPlayer  && t.getId()!= Id.decoration && t.getId() != Id.coin && t.getId() != Id.invBorder )
			{
				if(getBottom().intersects(t.getBounds()))
				{
					setpY(0);
					
					
					
					if(falling)
					{
						falling=false;
					}
				}
				else
				{
					
					if(!falling)
					{
						y-=1;
						gravity=0.0;
						falling=true;
					}
				}
			}
			if(t.getId()== Id.bullet)
			{
				if(t.getBounds().intersects(getBounds()))
				{
					t.die();
				}
			}
		}
		
		
		for(int i=0;handler.entity.size()>i;i++)
		{
			Entity t= handler.entity.get(i);
			
			if(t.getId() == Id.crate   )
			{
				if(((Crate)t).number != this.number)
				{
					if(getBottom().intersects(t.getBounds()))
					{
						setpY(0);
						
						
						
						if(falling)
						{
							falling=false;
						}
					}
					else
					{
						
						if(!falling)
						{
						
							gravity=0.0;
							falling=true;
						}
					}
				}
				
			}
		}
		
	}

	private void gravitacija()
	{

		if(falling)
		{
			
			gravity+=0.1;
			setpY((int)gravity);
		}

		
	}
	
	public Rectangle getLeftCrate()
	{
		
		return new Rectangle(x-4,y+10,5,height-20);
	}
	
	public Rectangle getRightCrate()
	{
		
		return new Rectangle(x+width,y+10,10,height-20);
	}
}
