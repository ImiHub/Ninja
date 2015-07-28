package sem.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;

import sem.game.*;
import sem.game.entity.*;

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
						
						if(!en.jumping)
							{
								en.jumping=true;
								en.gravity=8;
								
							}
						
						break;
				
						
					case KeyEvent.VK_DOWN:
					
						
						break;
						
						
					case KeyEvent.VK_LEFT:
						en.setpX(-4);	
						en.facing=1;
						break;
						
					case KeyEvent.VK_RIGHT:
						en.setpX(4);
						en.facing=0;
						
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
						
					
						
						break;
				
						
					case KeyEvent.VK_DOWN:
						
						
						break;
						
						
					case KeyEvent.VK_LEFT:
						en.setpX(0);	
						en.facing=3;
						break;
						
					case KeyEvent.VK_RIGHT:
						en.setpX(0);	
						en.facing=3;
						break;
						
						
						
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
