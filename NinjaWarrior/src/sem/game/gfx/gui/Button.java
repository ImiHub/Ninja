package sem.game.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JButton;
import sem.game.Game;

public class Button extends JButton
{
	public int x;
	public int y;
	public int width;
	public int height;
	
	public String label;
	
	public Button(int x, int y, int width, int height, String label)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}
	
	public void render (Graphics graphics)
	{
		graphics.setColor(new Color(13, 39, 20)); // novo
		graphics.setFont(new Font("Century Gothic", Font.TRUETYPE_FONT, 50));
	//	graphics.drawRect(getX() + 50, getY(), getWidth() - 250, getHeight());
		
		//aligns text
		
		FontMetrics fontMetrics = graphics.getFontMetrics();
		
		int stringX = (getWidth() - fontMetrics.stringWidth(getLabel())) / 2;
		int stringY = (fontMetrics.getAscent() + (getHeight() - (fontMetrics.getDescent() + fontMetrics.getAscent())) / 2);
		
		graphics.drawString(getLabel(), getX() + stringX, getY() + stringY);
	}
	
	// novoooo, sredjen mis i dugmad
	
	public void triggerEvent()
	{
		if (!Game.playing || Game.gameOver) 
		{
			if (getLabel().equals("New Game")) Game.newGame();
			else if (getLabel().equals("Exit Game")) System.exit(0);	
		}
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

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
}

