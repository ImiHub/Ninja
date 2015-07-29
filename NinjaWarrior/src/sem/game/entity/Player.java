package sem.game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.entity.enemy.SimpleEnemy;
import sem.game.tile.Tile;



public class Player extends Entity
{

	private int frame =0;
	private int frameDelay=0;
	
	
	
	public Player(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
		System.out.println(width+" " +height);
		facing=3;
	}

	@Override
	public void render(Graphics g)
	{
		
		if(facing==3)
		{
			
			
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/idle__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width-15,height,null);

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					}
		else if(facing==1)
		{
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/run__01"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(facing==0)
		{
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/run__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(facing==45)
		{
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/jump__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		//g.drawImage(Game.player.getBufferedImage(), x, y, width,height,null);
		
	}

	@Override
	public void update()
	{
		x+=pX;
		y+=pY;
		
		
		
		tileIntersect();
		entityIntersect();
		gravitacija();
		animacija();
		
		
	}

	private void entityIntersect()
	{
				
		for(int i =0 ; handler.entity.size()>i;i++)
		{
			
			
			Entity e = handler.entity.get(i);
			if(e.getId() == Id.simpleEnemy)
			{
				simpleEnemyIntersect(e);
				
			}
		
		}
		
	}

	private void simpleEnemyIntersect(Entity e)
	{
		if(getBottom().intersects(e.getTop()))
		{
			e.die();
		}
		else if(getBounds().intersects(e.getBounds()))
		{
			
					this.die();
			
		}
		
	}

	private void animacija()
	{
		frameDelay++;
		if(frameDelay>=6)
		{
			frame++;
			if(frame>=10)
			{
				frame=0;
			}
			frameDelay=0;
		}
		
	}

	private void gravitacija()
	{
		
		
		if(jumping)
		{
			gravity-=0.1;
			setpY((int)-gravity);
			if(gravity<=0.0)
			{
				jumping=false;
				falling=true;
			}
			
		}
		if(falling)
		{
		
			gravity+=0.1;
			setpY((int)gravity);
		}
		
		



		
	}

	private void tileIntersect()
	{
		for(int i =0 ; handler.tile.size()>i;i++)
		{
			Tile t = handler.tile.get(i);
			if(t.getId() == Id.wall)
			{
				wallIntersect(t);
			}
			else if (t.getId() == Id.coin)
			{
				coinIntersect(t);
			}
			else if(t.getId() == Id.water)
			{
				waterIntersect(t);
			}
		}
		
		
		
		
		
	}

	private void waterIntersect(Tile t)
	{
		if(getBottom().intersects(t.getBounds()))
		{
			this.die();
		}
		
	}

	private void coinIntersect(Tile tt)
	{
		
		if (getBounds().intersects(tt.getBounds()) )
		{
				Game.coins++;
				tt.die();
				
		}
		
	}

	private void wallIntersect(Tile t)
	{
		
		if(getTop().intersects(t.getBounds()))
		{
			setpY(0);
			y =  t.y + t.height+36;
			if(jumping )
			{
			
				jumping=false;
				gravity=0.8;
				falling=true;
			}
		
		}
		if(getBottom().intersects(t.getBounds()))
		{
			setpY(0);
			y = t.y - t.height-36;
			
			
			if(falling)
			{
				falling=false;
				numberOfJumps=2;
			}
		}
		else
		{
			if(!falling && !jumping)
			{
				gravity=0.0;
				falling=true;
			}
		}
		
		if(getRight().intersects(t.getBounds()))
		{
		
			setpX(0);
			x = t.x -t.width-7;
			
	
			
		}
		if(getLeft().intersects(t.getBounds()))
		{
			
			setpX(0);
			x = t.x +t.width;
			System.out.println("levo");
		}
		
	}
	

}
