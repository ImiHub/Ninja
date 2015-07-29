package sem.game.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import sem.game.Handler;
import sem.game.Id;

public abstract class Tile
{
	
	public int x,y;
	public int width,height;
	public int pX,pY;
	public Id id;
	
	public boolean solid;
	public Handler handler;

	public Tile(int x, int y, int width, int height,Id id,Handler h)
	{
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		handler=h;
	}
	
	public abstract void render(Graphics g);
	public abstract void update();
	
	

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
	
	
	
	public int getpX()
	{
		return pX;
	}

	public void setpX(int pX)
	{
		this.pX = pX;
	}

	public int getpY()
	{
		return pY;
	}

	public void setpY(int pY)
	{
		this.pY = pY;
	}

	public Id getId()
	{
		return id;
	}

	public void setId(Id id)
	{
		this.id = id;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,width,height);
	}

	public void die()
	{
		handler.tile.remove(this);
		
	}
	
	
	
}
