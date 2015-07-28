package sem.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import sem.game.entity.Entity;
import sem.game.entity.Player;
import sem.game.entity.enemy.SimpleEnemy;
import sem.game.tile.Decoration;
import sem.game.tile.Tile;
import sem.game.tile.Wall;


public class Handler
{
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	public Handler()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(int i =0 ; tile.size() >i;i++)
		{
			tile.get(i).render(g);
		}
		for(int i =0 ; entity.size() >i;i++)
		{
			entity.get(i).render(g);
		}
		
	
		
		
	}
	
	public void update()
	{
		for(int i=0;entity.size()>i;i++)
		{
			Entity en  = entity.get(i);
			en.update();
		}
		
		
		for(Tile en:tile)
		{
			en.update();
		}
		
		
		
	}
	
	public void addEntity(Entity e)
	{
		entity.add(e);
	}
	
	public void addTile(Tile t)
	{
		tile.add(t);
	}
	
	public void removeEntity(Entity e)
	{
		entity.remove(e);
	}
	public void removeTile(Tile e)
	{
		tile.remove(e);
	}
	
	public  void createLevel(BufferedImage level)
	{
		int width = level.getWidth();
		int height = level.getHeight();
		
		
		for(int y=0;y<height;y++)
		{
			for(int x=0; x<width;x++)
			{
				int pixel  =level.getRGB(x, y);
				
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				
				if(red==0 && (green>=100 && green <=120) && blue==0) addTile(new Wall(x*64,y*64,64,64,Id.wall,this,green-100));
				if((red>=100 && red<=120) && green==0 && blue==0) addTile(new Decoration(x*64, y*64, 64, 64, Id.decoration, this, red-100));
			
				if(red==0 && green==0 && blue==255) addEntity(new Player(x*70,y*100,70,100,Id.player,this));
				if(red==0 && green==200 && blue==200) addEntity(new SimpleEnemy(x*64, y*64, 64, 64, Id.simpleEnemy, this));
					
			}
		}
	}
	
	

}
