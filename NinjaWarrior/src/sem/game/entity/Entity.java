package sem.game.entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import sem.game.Handler;
import sem.game.Id;

public  abstract class Entity
{
	
	public int x,y;
	public int width,height;
	public int pX,pY;
	public Id id;
	
	public boolean solid;
	public Handler handler;
	public boolean falling;
	public int facing;
	public boolean jumping;
	public double gravity = 0;
	public int numberOfJumps;

	public Entity(int x, int y, int width, int height,Id id,Handler h)
	{
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id=id;
		handler=h;
	}
	
	public abstract void render(Graphics g);
	public abstract void update();
	
	
	//========================GETTERS AND SETTERS ==================================================

	public int getX()
	{
		return x;
	}

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
	
	
	
	public Rectangle getBottom()
	{
		return new Rectangle(x+10,y+height-5,width-20,5);
	}
	
	public Rectangle getTop()
	{
		return new Rectangle(x+10,y,width-20,5);
	}
	
	public Rectangle getRight()
	{
		return new Rectangle(x+width-5,y+10,5,height-20);
	}
	
	public Rectangle getLeft()
	{
		
		return new Rectangle(x,y+10,5,height-20);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,width,height);
	}

	public void die()
	{
		handler.removeEntity(this);
		
	}
	
	
	
}
