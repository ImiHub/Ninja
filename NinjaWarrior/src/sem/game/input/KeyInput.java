package sem.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JEditorPane;

import sem.game.*;
import sem.game.entity.*;
import sem.game.tile.Bullet;

public class KeyInput implements KeyListener
{	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(Entity en: Game.handler.entity)
		{
			
			if(en.getId()==Id.player)
			{
				switch (key)
				{
					case KeyEvent.VK_UP:
						
						
						if(en.numberOfJumps==2 && !en.jumping)
							{
								en.numberOfJumps--;
								en.jumping=true;
								if(en.facing == -1 || en.facing == -3) en.facing=-4;
								else en.facing=4;
								
								en.falling=false;
								en.gravity=8;
							}
						if(en.numberOfJumps==1 && en.doubleJump)
						{
							en.doubleJump=false;
							en.numberOfJumps--;
							en.jumping=true;
							if(en.facing == -1 || en.facing == -3) en.facing=-4;
							else en.facing=4;
							
							en.falling=false;
							en.gravity=6;
						}
						break;
				
						
					case KeyEvent.VK_DOWN:
						
						((Player) en).teleport();
						
						break;
						
						
					case KeyEvent.VK_LEFT:
						en.setpX(-4);	
						en.facing=-1;
						((Player)en).mleft=true;
						break;
						
					case KeyEvent.VK_RIGHT:
						en.setpX(4);
						en.facing=1;
						((Player)en).mright=true;
						
						break;
						
					case KeyEvent.VK_M:
						
						if(((Player)en).small) ((Player)en).small();
						//ovde ispitujemo koja moc je aktivna
						break;
						
					
						
						
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(Entity en: Game.handler.entity)
		{
			
			if(en.getId()==Id.player)
			{
				switch (key)
				{
					case KeyEvent.VK_UP:
						if(en.numberOfJumps==1) en.doubleJump=true;
						break;
				
						
					case KeyEvent.VK_DOWN:
						
						
						break;
						
						
					case KeyEvent.VK_LEFT:
						en.setpX(0);	
						en.facing=-3;
						((Player)en).mleft=false;
						break;
						
					case KeyEvent.VK_RIGHT:
						en.setpX(0);	
						en.facing=3;
						((Player)en).mright=false;
						break;
					case KeyEvent.VK_SPACE: //za pucu
						 
						if (((Player)en).isShooting() && ((Player)en).getMunition() > 0) 
						{
							Game.handler.addTile(new Bullet(en.getX()+20, en.getY() + 35, 40, 16, Id.bullet, Game.handler, en.facing));
							((Player)en).setMunition(((Player)en).getMunition()-1);
							if (((Player)en).getMunition() == 0) ((Player)en).setShooting(false);
						}
						
					break;	
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	    
			
	}

	
	
	
	
}
