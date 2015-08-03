package sem.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import sem.game.entity.Crate;
import sem.game.entity.Entity;
import sem.game.entity.Player;
import sem.game.entity.enemy.SimpleEnemy;
import sem.game.tile.Coin;
import sem.game.tile.Decoration;
import sem.game.tile.InvBorder;
import sem.game.tile.KunaiAmo;
import sem.game.tile.Small;
import sem.game.tile.Teleport;
import sem.game.tile.Tile;
import sem.game.tile.Wall;
import sem.game.tile.Water;

public class Handler
{
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	public LinkedList<Teleport> teleport = new LinkedList<Teleport>(); 
	
	public Handler()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < tile.size(); i++)
		    {
		    	int xx = Camera.xPlayer;
		    	int x = tile.get(i).getX();
		    	if(x>= xx-1000 && x<= xx+1000)
		    	tile.get(i).render(g);
		    }
		
		for(int i = 0; i < entity.size(); i++)
		    {
		    	int xx = Camera.xPlayer;
		    	int x = entity.get(i).getX();
		    	if(x>= xx-1000 && x<= xx+1000)
		    	entity.get(i).render(g);
		    }
	}
	
	public void update()
	{
		for (int i=0;entity.size()>i;i++)
		{
			Entity en  = entity.get(i);
			int xx = Camera.xPlayer;
		    	int x = en.getX();
		    	if(x>= xx-1000 && x<= xx+1000)
			en.update();
		}
		
		for(int i=0;tile.size()>i;i++)
		{
			Tile t =  tile.get(i);
			int xx = Camera.xPlayer;
		    	int x = t.getX();
		    	if(x>= xx-1000 && x<= xx+1000)
			t.update();
		}
	}
	
	public  void createLevel(BufferedImage level)
	{
		int width = level.getWidth();
		int height = level.getHeight();
		
		for (int y = 0 ;y < height; y++)
		{
			for (int x=0; x < width; x++)
			{
				int pixel = level.getRGB(x, y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 0 && (green >= 100 && green <= 120) && blue == 0) addTile(new Wall(x*64,y*64,64,64,Id.wall,this,green-100));
				if ((red >= 100 && red <= 120) && green == 0 && blue == 0) addTile(new Decoration(x*64, y*64, 64, 64, Id.decoration, this, red-100));
			
				if (red == 0 && green == 0 && blue == 255) addEntity(new Player(x*70,y*100,70,100,Id.player,this));
				if (red == 0 && green == 200 && blue == 200) addEntity(new SimpleEnemy(x*64, y*64, 64, 64, Id.simpleEnemy, this));
				if (red == 255 && green == 255 && blue == 0) addTile(new Coin(x*64, y*64, 64, 64, Id.coin, this));	
				if (red == 0 && green == 0 && (blue == 100 || blue == 99)) addTile(new Water(x*64,y*64,64,64,Id.water,this,blue-85));
				if (red == 0 && green == 255 && blue == 240) addTile(new Small(x*64, y*64, 64, 64, Id.smallPlayer, this));
				
				if (red == 255 && green == 0 && blue == 0) addTile(new KunaiAmo(x*64, y*64, 100, 100, Id.kunai_coin, this)); // novo, ostavi pod kom do daljneg
			//	if (red == 255 && green == 150 && blue == 255) addEntity(new TeleportButton(x*64, y*64, 64, 64, Id.teleportButton, this)); //ostavi pod kom do daljneg
				
				if (red == 250 && green == 150 && blue == 20) addEntity(new Crate(x*64, y*64, 128, 128, Id.crate, this));
				if (red == 250 && green == 150 && blue == 21) addEntity(new Crate(x*64, y*64, 64, 64, Id.crate, this));

				if(red == 105 && green == 115 && blue == 105) addTile(new InvBorder(x*64+30, y*64, 64-50, 64, Id.invBorder, this)); 
				
			}
		}
		
		Teleport t[]=new Teleport[2];
		int i=0;
		
		for(int y=0;y<height;y++)
			for(int x=0; x<width;x++)
			{
				int pixel  = level.getRGB(x, y);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red==255 && green==0 && blue==255) 
				{
					t[i]=new Teleport(x*64, y*64+10-64, 90, 128, Id.teleport, this);
					addTile(t[i]);
					addTeleport(t[i++]);
				}
			}
		
		t[0].setDestX(t[1].x);
		t[0].setDestY(t[1].y);
		t[1].setDestX(t[0].x);
		t[1].setDestY(t[0].y);
	}
	
	public void clearLevel()
	{
		entity.clear();
		tile.clear();
	}
	
	public void addEntity(Entity e)
	{
		entity.add(e);
	}
	
	public void addTile(Tile t)
	{
		tile.add(t);
	}
	
	public void addTeleport(Teleport t)
	{
		teleport.add(t);
	}
	
	public void removeEntity(Entity e)
	{
		entity.remove(e);
	}
	
	public void removeTile(Tile e)
	{
		tile.remove(e);
	}
	
	public void removeTeleport(Teleport e)
	{
		teleport.remove(e);
	}
}