package sem.game;

import sem.game.entity.Entity;
import sem.game.entity.Player;

public class Camera
{
	public int x;
	public int y;
	public int pomY;
	public static int xPlayer; //deathScreen
	public static int yPlayer; //deathScreen
	
	public boolean shooting;
	public int munition;
	
	public void update(Entity player)
	{
		xPlayer = player.getX(); 
		yPlayer = player.getY(); 
		
		shooting = ((Player)player).isShooting();
		munition = ((Player)player).getMunition();
		
		if (player.getX() > 542) setX(-player.getX() + Game.WIDTH * 2);
		if (player.getY() < 1250) setY(-player.getY() + Game.HEIGHT * 2 - 50);
		else pomY=-yPlayer+1250;
	}

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
}
