package sem.game;

import sem.game.entity.Entity;

public class Camera
{
	public int x;
	public int y;
	public static int xPlayer; //deathScreen
	public static int yPlayer; //deathScreen
	
	public void update(Entity player)
	{
		xPlayer = player.getX(); 
		yPlayer = player.getY(); 
		
		if (player.getX() > 542) setX(-player.getX() + Game.WIDTH * 2);
		if (player.getY() < 3000) setY(-player.getY() + Game.HEIGHT * 2 - 50); //ovdee
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
