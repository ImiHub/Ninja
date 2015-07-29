package sem.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.Box;

import sem.game.entity.Entity;
import sem.game.entity.Player;
import sem.game.entity.enemy.SimpleEnemy;
import sem.game.tile.Crate;
import sem.game.tile.Coin;
import sem.game.tile.Decoration;
import sem.game.tile.Small;
import sem.game.tile.Tile;
import sem.game.tile.Wall;
import sem.game.tile.Water;


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
		
		
		for(int i=0;tile.size()>i;i++)
		{
			Tile t =  tile.get(i);
			t.update();
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
				if (red == 255 && green == 255 && blue == 0) addTile(new Coin(x*64, y*64, 64, 64, Id.coin, this));	
				if(red==0 && green==0 && (blue==100 || blue==99)) addTile(new Water(x*64,y*64,64,64,Id.water,this,blue-85));
				if(red==0 && green==255 && blue==240) addTile(new Small(x*64, y*64, 64, 64, Id.smallPlayer, this));
				if(red==250 && green==150 && blue==20) addTile(new Crate(x*64, y*64, 128, 128, Id.crate, this));
				if(red==250 && green==150 && blue==21) addTile(new Crate(x*64, y*64, 64, 64, Id.crate, this));
				
			}
		}
	}
	
	

}
