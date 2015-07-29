package sem.game;

import sem.game.entity.Entity;




public class Camera
{
	//dfs
	public int x,y;
	
	public void update(Entity player)
	{
		if(player.getX()>542 && player.getX()<5005) setX(-player.getX()+Game.WIDTH*2);
		if(player.getY()<450) setY(-player.getY()+Game.HEIGHT*2-50);
		
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
