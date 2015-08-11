package sem.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import sem.game.Game;
import sem.game.Handler;
import sem.game.Id;
import sem.game.tile.Teleport;
import sem.game.tile.TeleportButton;
import sem.game.tile.Tile;

public class Player extends Entity
{
	public boolean mleft = false;
	public boolean mright = false;
	public int frame = 0;
	int oldFacing = 0;
	private int frameDelay = 0;
	boolean state = false;
	Timer timerSmall = new Timer();
	public boolean small=false;
	
	private int munition = 0; // novo
	private boolean shooting = false; //novo
	private boolean shootingPressed = false;
	
	private int health = 100; //novo + geteri i seteri za njih dole
	
	
	public Player(int x, int y, int width, int height, Id id, Handler h)
	{
		super(x, y, width, height, id, h);
	//f	System.out.println(width+" " +height);
		facing = 3;
	}

	public void render(Graphics g)
	{
		if (facing == 3)
		{		
			if (shooting)
			{
				g.drawImage(Game.playerThrowRight[frame],x, y, width,height,null);
			}
			
		    oldFacing=3;
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/idle__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width-15,height,null);

			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		else if (facing == -3)
		{
			if (shooting)
			{
				
			}
			
			
		    oldFacing=-3;
			try
			{		
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/idle__01"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width-15,height,null);
			} 
			catch (IOException e)
			{
				e.printStackTrace();				
			}
		}
		
		else if (facing == -1)
		{
			if (shooting)
			{
				
			}
			
		    oldFacing=-1;
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/run__01"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		else if (facing == 1)
		{
			if (shooting)
			{
				
			}
			
		    oldFacing=1;
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/run__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		else if (facing == -4)
		{
			
				g.drawImage(Game.playerJumpLeft[frame],x, y, width,height,null);
		    		if(frame==9)
		    		{
		    		    if(oldFacing==-3) facing=-3;
		    		    else facing=-1;
		    		}
			
		}
		else if (facing == 4)
		{
		    			
				g.drawImage(Game.playerJumpRight[frame],x, y, width,height,null);
				if(frame==9)
		    		{
		    		    if(oldFacing==3) facing=3;
		    		    else facing=1;
		    		}
			
		}
		
		else if (facing == 45)
		{
			try
			{
				
				BufferedImage sheet;
				sheet = ImageIO.read(getClass().getResource("/idle/jump__00"+frame+"-min.png"));
				g.drawImage(sheet,x, y, width,height,null);

			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		//g.drawImage(Game.player.getBufferedImage(), x, y, width,height,null);
	}

	@Override
	public void update()
	{
		x += pX;
		y += pY;
		
		tileIntersect();
		entityIntersect();
		gravitacija();
		animacija();
	}

	private void entityIntersect()
	{
				
		for(int i = 0 ; handler.entity.size()>i;i++)
		{
			
			
			Entity e = handler.entity.get(i);
			if(e.getId() == Id.simpleEnemy)
			{
				simpleEnemyIntersect(e);
				
			}
			else if(e.getId() == Id.crate)
			{
				crateIntersect(e);
			}
		
		}
		
	}

	private void simpleEnemyIntersect(Entity e)
	{
		if(getBottom().intersects(e.getTop()))
		{
			System.out.println(2424);
			e.die();
		}
		else if(getBounds().intersects(e.getBounds()))
		{
			System.out.println(5555);
			setHealth(0); // novo
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
			else if(t.getId() == Id.smallPlayer)
			{
				smallPlayerIntersect(t);
			}
			else if(t.getId() == Id.kunai_coin)
			{
				kunaiAmoIntersect(t);
			}
			
		}
		
		
		
		
		
	}

	private void crateIntersect(Entity e)
	{
		if(getTop().intersects(e.getBounds()))
		{
			setpY(0);
			if(state)	
				y =  e.y + 64;
			else
			{
				y =  e.y + (64+36);
				
			}
				
			
			if(jumping )
			{
			
				jumping=false;
				gravity=0.8;
				falling=true;
			}
			
		}
		if(getBottom().intersects(e.getBounds()))
		{
			setpY(0);
			
			if(state)	
				y = e.y - 64;
			
			else
				y = e.y - 64-36;
			
			
			if(falling)
			{
				falling=false;
				doubleJump=false;
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
		
		if(getRight().intersects(e.getBounds()))
		{
		
			setpX(0);
			
			x = e.x -64-7;
			if(mright)
			{
				if(checkCrateRight(e))
				e.setX(e.x+4);
			}
			else
			{
				e.setpX(0);
			}
	
			
		}
		if(getLeft().intersects(e.getBounds()))
		{
			
			setpX(0);
			x = e.x +e.width;
			if(mleft)
			{
				if(checkCrateLeft(e))
				e.setX(e.x-4);
			}
		}
		
	}

	private boolean checkCrateLeft(Entity e)
	{
		if(checkCrateTile(e,false)) return false;
		if(checkCrateEntity(e,false)) return false;
		
		return true;
	}

	private boolean checkCrateRight(Entity e)
	{
		
		
		if(checkCrateTile(e,true)) return false;
		if(checkCrateEntity(e,true)) return false;
		
		return true;
		
		
		
	}

	private boolean checkCrateEntity(Entity ee,Boolean side)
	{
		for(int i=0;handler.entity.size()>i;i++)
		{
			Entity e = handler.entity.get(i);
			if(e.getId() != Id.player )
			{
				if(e.getId()==Id.crate)
				{
					if(((Crate)e).number != ((Crate)ee).number)
					{
					
						if(side)
						{
							if(e.getBounds().intersects(((Crate)ee).getRightCrate()))
							{
								return true;
							}
						}
						else
						{
							if(e.getBounds().intersects(((Crate)ee).getLeftCrate()))
							{
								
								return true;
							}
						}
					}
				}
				else
				{
					if(side)
					{
						if(e.getBounds().intersects(((Crate)ee).getRightCrate()))
						{
							return true;
						}
					}
					else
					{
						if(e.getBounds().intersects(((Crate)ee).getLeftCrate()))
						{
							
							return true;
						}
					}
				}
				
				
			}
		
		}
		
		
		return false;
	}

	private boolean checkCrateTile(Entity ee,boolean side)
	{
		for(int i=0;handler.tile.size()>i;i++)
		{
			Tile e = handler.tile.get(i);
			if(e.getId()!= Id.decoration && e.getId()!=Id.coin && e.getId()!= Id.invBorder  )
			{
				if(side)
				{
					if(e.getBounds().intersects(((Crate)ee).getRightCrate()))
					{
						return true;
					}
				}
				else
				{
					if(e.getBounds().intersects(((Crate)ee).getLeftCrate()))
					{
						System.out.println(2222);
						return true;
					}
				}
			}
			
		}
		return false;
	}

	private void smallPlayerIntersect(Tile t)
	{
		if(getBounds().intersects(t.getBounds()))
		{
			t.die();
			small=true;
		}
		
	}
	
	public void small()
	{
		small=false;
		height=64;
		width=45;
		state=true;
		timerSmall.schedule(new TimerTask() {
			  public void run() {
			   state=false;
			   height=100;
			   width=70;
			  }
			}, 10000);
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
			if(state)	
				y =  t.y + t.height;
			else
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
			
			if(state)	
				y = t.y - t.height;
			else
				y = t.y - t.height-36;
			
			
			if(falling)
			{
				falling=false;
				doubleJump=false;
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
			y = t.y + t.height-height; //ovo
			
		}
		
	}
	
	public void teleport()
	{
		
		for(int i =0 ; handler.teleport.size()>i;i++)
		{
			Teleport t = handler.teleport.get(i);
			if(t.getId()==Id.teleport)
			{
				if(teleportIntersect(t)) break;
			}
		}
	
	}
	
	private boolean teleportIntersect(Tile t)
	{
		if(t.x<x && t.x+t.width>x && t.y+t.height-10==y+height)
		{
				x = ((Teleport)(t)).getDestX()+15;
				y = ((Teleport)(t)).getDestY()+15; //ovo
				System.out.println("tel dest = " +  x + "  " + y);
				return true;
			
		}
		return false;
		
	}

	private boolean buttonIntersect(Tile t)
	{
		if(t.x<x && t.x+t.width>x && t.y+t.height==y+height)
		{
			((TeleportButton) t).setPressed(true);
			return true;
		}
		else
		{
			((TeleportButton) t).setPressed(false);
			return false;
		}
	}
	
	private void kunaiAmoIntersect(Tile t)
	{
		if (getBounds().intersects(t.getBounds()))
		{
				shooting = true;
				munition = 10;
				t.die();
		}
	}
	
	public int getMunition() {
		return munition;
	}

	public void setMunition(int munition) {
		this.munition = munition;
	}

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
